package com.inventory.inventory_service.mapper;

import com.inventory.inventory_service.dto.StockBalanceResponseDto;
import com.inventory.inventory_service.dto.StockJournalResponseDto;
import com.inventory.inventory_service.entity.StockBalance;
import com.inventory.inventory_service.entity.StockJournal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {

    StockBalanceResponseDto toBalanceResponse(StockBalance entity);

    StockJournalResponseDto toJournalResponse(StockJournal entity);
}