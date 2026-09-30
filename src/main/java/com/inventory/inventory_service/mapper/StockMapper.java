package com.inventory.inventory_service.mapper;

import com.inventory.inventory_service.dto.StockBalanceResponse;
import com.inventory.inventory_service.dto.StockJournalResponse;
import com.inventory.inventory_service.entity.StockBalance;
import com.inventory.inventory_service.entity.StockJournal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {

    StockBalanceResponse toBalanceResponse(StockBalance entity);

    StockJournalResponse toJournalResponse(StockJournal entity);
}