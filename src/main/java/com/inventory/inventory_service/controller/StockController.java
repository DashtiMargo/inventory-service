package com.inventory.inventory_service.controller;

import com.inventory.inventory_service.dto.StockBalanceResponse;
import com.inventory.inventory_service.dto.StockJournalResponse;
import com.inventory.inventory_service.dto.StockOperationRequest;
import com.inventory.inventory_service.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping("/incoming")
    public ResponseEntity<StockBalanceResponse> incoming(@RequestBody StockOperationRequest request) {
        return ResponseEntity.ok(stockService.increaseStock(request));
    }

    @PostMapping("/outgoing")
    public ResponseEntity<StockBalanceResponse> outgoing(@RequestBody StockOperationRequest request) {
        return ResponseEntity.ok(stockService.decreaseStock(request));
    }

    @GetMapping("/balance")
    public ResponseEntity<StockBalanceResponse> balance(@RequestParam String itemCode,
                                                        @RequestParam String warehouseId) {
        return ResponseEntity.ok(stockService.getBalance(itemCode, warehouseId));
    }

    @GetMapping("/journal")
    public ResponseEntity<List<StockJournalResponse>> journal(
            @RequestParam(required = false) String productCode,
            @RequestParam(required = false) String warehouseId) {
        return ResponseEntity.ok(stockService.getJournal(productCode, warehouseId));
    }
}