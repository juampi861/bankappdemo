package com.juampi861.bankappdemo.bank.infra.rest.controller;

import com.juampi861.bankappdemo.bank.application.BankService;
import com.juampi861.bankappdemo.bank.domain.model.Bank;
import com.juampi861.bankappdemo.bank.infra.rest.dto.*;
import com.juampi861.bankappdemo.bank.infra.rest.mapper.BankWebMapper;
import com.juampi861.bankappdemo.bank.infra.restclient.BankRestClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Bank Management", description = "Bank Endpoints")
@RestController
@RequestMapping("/banks")
@RequiredArgsConstructor
@Validated
public class BankController {

    private final BankService bankService;
    private final BankRestClient bankRestClient;

    @Operation(summary = "Create a new bank")
    @ApiResponse(responseCode = "201", description = "Bank created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "409", description = "Bank already exists")
    @PostMapping
    public ResponseEntity<Void> createBank(@RequestBody @Valid BankRequestDTO dto) {
        bankService.createBank(BankWebMapper.toDomain(dto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Update a bank by ID")
    @ApiResponse(responseCode = "204", description = "Bank updated successfully")
    @ApiResponse(responseCode = "400", description = "ID mismatch or invalid input")
    @ApiResponse(responseCode = "404", description = "Bank not found")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBank(@PathVariable String id, @RequestBody @Valid BankRequestDTO dto) {
        if (!id.equals(dto.id())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        bankService.updateBankDetails(BankWebMapper.toDomain(dto));
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get a bank by ID")
    @ApiResponse(responseCode = "200", description = "Bank found")
    @ApiResponse(responseCode = "404", description = "Bank not found")
    @GetMapping("/{id}")
    public ResponseEntity<BankResponseDTO> getBankById(@PathVariable String id) {
        final Bank bank = bankService.getBankById(id).orElseThrow();
        return ResponseEntity.ok(BankWebMapper.toResponseDTO(bank));
    }

    @Operation(summary = "Get all banks")
    @ApiResponse(responseCode = "200", description = "List of banks")
    @GetMapping
    public ResponseEntity<List<BankResponseDTO>> getAllBanks() {
        final List<BankResponseDTO> banks = bankService.getAllBanks()
                .stream()
                .map(BankWebMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(banks);
    }

    @Operation(summary = "Delete a bank by ID")
    @ApiResponse(responseCode = "204", description = "Bank deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBank(@PathVariable String id) {
        bankService.deleteBankById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Call internal endpoint to get a bank by ID")
    @ApiResponse(responseCode = "200", description = "Internal bank lookup successful")
    @GetMapping("/internal/{id}")
    public ResponseEntity<BankResponseDTO> getBankByIdInternal(@PathVariable String id) {
        final BankResponseDTO bank = bankRestClient.getBankById(id);
        return ResponseEntity.ok(bank);
    }
}
