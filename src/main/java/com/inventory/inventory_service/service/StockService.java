package com.inventory.inventory_service.service;

import com.inventory.inventory_service.dto.StockBalanceResponseDto;
import com.inventory.inventory_service.dto.StockJournalResponseDto;
import com.inventory.inventory_service.dto.StockOperationRequestDto;
import com.inventory.inventory_service.entity.OperationType;
import com.inventory.inventory_service.entity.StockBalance;
import com.inventory.inventory_service.entity.StockJournal;
import com.inventory.inventory_service.exception.BadRequestException;
import com.inventory.inventory_service.exception.NotEnoughStockException;
import com.inventory.inventory_service.exception.StockNotFoundException;
import com.inventory.inventory_service.mapper.StockMapper;
import com.inventory.inventory_service.repository.StockBalanceRepository;
import com.inventory.inventory_service.repository.StockJournalRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StockService {

    StockBalanceRepository stockBalanceRepository;
    StockJournalRepository stockJournalRepository;
    StockMapper stockMapper;

    @Transactional
    public StockBalanceResponseDto increaseStock(StockOperationRequestDto request) {
        Optional<StockBalance> found = stockBalanceRepository
                .findByProductCodeAndWarehouseId(request.productCode(), request.warehouseId());

        StockBalance balance;
        if (found.isPresent()) {
            balance = found.get();
            balance.setQuantity(balance.getQuantity() + request.quantity());
        } else {
            balance = new StockBalance();
            balance.setProductCode(request.productCode());
            balance.setWarehouseId(request.warehouseId());
            balance.setQuantity(request.quantity());
        }

        StockBalance saved = stockBalanceRepository.save(balance);

        StockJournal journal = new StockJournal();
        journal.setProductCode(request.productCode());
        journal.setWarehouseId(request.warehouseId());
        journal.setOperationType(OperationType.INCOMING);
        journal.setQuantity(request.quantity());
        journal.setDocumentId(request.documentId());
        stockJournalRepository.save(journal);

        return stockMapper.toBalanceResponse(saved);
    }

    @Transactional
    public StockBalanceResponseDto decreaseStock(StockOperationRequestDto request) {
        StockBalance balance = stockBalanceRepository
                .findByProductCodeAndWarehouseId(request.productCode(), request.warehouseId())
                .orElseThrow(StockNotFoundException::new);

        if (balance.getQuantity() < request.quantity()) {
            throw new NotEnoughStockException();
        }

        balance.setQuantity(balance.getQuantity() - request.quantity());
        StockBalance saved = stockBalanceRepository.save(balance);

        StockJournal journal = new StockJournal();
        journal.setProductCode(request.productCode());
        journal.setWarehouseId(request.warehouseId());
        journal.setOperationType(OperationType.OUTGOING);
        journal.setQuantity(request.quantity());
        journal.setDocumentId(request.documentId());
        stockJournalRepository.save(journal);

        return stockMapper.toBalanceResponse(saved);
    }

    public StockBalanceResponseDto getBalance(String productCode, String warehouseId) {
        StockBalance balance = stockBalanceRepository
                .findByProductCodeAndWarehouseId(productCode, warehouseId)
                .orElseThrow(StockNotFoundException::new);
        return stockMapper.toBalanceResponse(balance);
    }

    public List<StockJournalResponseDto> getJournal(String productCode, String warehouseId) {
        List<StockJournal> list;

        if (productCode != null && !productCode.isEmpty()) {
            list = stockJournalRepository.findByProductCode(productCode);
        } else if (warehouseId != null && !warehouseId.isEmpty()) {
            list = stockJournalRepository.findByWarehouseId(warehouseId);
        } else {
            list = stockJournalRepository.findAll();
        }

        return list.stream()
                .map(stockMapper::toJournalResponse)
                .toList();
    }
}