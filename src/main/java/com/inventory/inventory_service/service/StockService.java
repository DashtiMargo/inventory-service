package com.inventory.inventory_service.service;

import com.inventory.inventory_service.dto.StockBalanceResponse;
import com.inventory.inventory_service.dto.StockJournalResponse;
import com.inventory.inventory_service.dto.StockOperationRequest;
import com.inventory.inventory_service.entity.OperationType;
import com.inventory.inventory_service.entity.StockBalance;
import com.inventory.inventory_service.entity.StockJournal;
import com.inventory.inventory_service.exception.BadRequestException;
import com.inventory.inventory_service.exception.StockNotFoundException;
import com.inventory.inventory_service.mapper.StockMapper;
import com.inventory.inventory_service.repository.StockBalanceRepository;
import com.inventory.inventory_service.repository.StockJournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockBalanceRepository stockBalanceRepository;
    private final StockJournalRepository stockJournalRepository;
    private final StockMapper stockMapper;

    @Transactional
    public StockBalanceResponse increaseStock(StockOperationRequest request) {
        Optional<StockBalance> found = stockBalanceRepository
                .findByProductCodeAndWarehouseId(request.getProductCode(), request.getWarehouseId());

        StockBalance balance;
        if (found.isPresent()) {
            balance = found.get();
            balance.setQuantity(balance.getQuantity() + request.getQuantity());
        } else {
            balance = new StockBalance();
            balance.setProductCode(request.getProductCode());
            balance.setWarehouseId(request.getWarehouseId());
            balance.setQuantity(request.getQuantity());
        }

        StockBalance saved = stockBalanceRepository.save(balance);

        StockJournal journal = new StockJournal();
        journal.setProductCode(request.getProductCode());
        journal.setWarehouseId(request.getWarehouseId());
        journal.setOperationType(OperationType.INCOMING);
        journal.setQuantity(request.getQuantity());
        stockJournalRepository.save(journal);

        return stockMapper.toBalanceResponse(saved);
    }

    @Transactional
    public StockBalanceResponse decreaseStock(StockOperationRequest request) {
        StockBalance balance = stockBalanceRepository
                .findByProductCodeAndWarehouseId(request.getProductCode(), request.getWarehouseId())
                .orElseThrow(StockNotFoundException::new);

        if (balance.getQuantity() < request.getQuantity()) {
            throw new BadRequestException();
        }

        balance.setQuantity(balance.getQuantity() - request.getQuantity());
        StockBalance saved = stockBalanceRepository.save(balance);

        StockJournal journal = new StockJournal();
        journal.setProductCode(request.getProductCode());
        journal.setWarehouseId(request.getWarehouseId());
        journal.setOperationType(OperationType.OUTGOING);
        journal.setQuantity(request.getQuantity());
        stockJournalRepository.save(journal);

        return stockMapper.toBalanceResponse(saved);
    }

    public StockBalanceResponse getBalance(String itemCode, String warehouseId) {
        StockBalance balance = stockBalanceRepository
                .findByProductCodeAndWarehouseId(itemCode, warehouseId)
                .orElseThrow(StockNotFoundException::new);
        return stockMapper.toBalanceResponse(balance);
    }

    public List<StockJournalResponse> getJournal() {
        return stockJournalRepository.findAll().stream()
                .map(stockMapper::toJournalResponse)
                .toList();
    }
}