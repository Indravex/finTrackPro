package indravex.FinTrack.Pro.controller;

import indravex.FinTrack.Pro.dto.TransactionRequest;
import indravex.FinTrack.Pro.dto.TransactionResponse;
import indravex.FinTrack.Pro.entity.Transaction;
import indravex.FinTrack.Pro.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(
            @Valid @RequestBody TransactionRequest request) {

        Transaction transaction =
                transactionService.createTransaction(request);

        TransactionResponse response = TransactionResponse.builder()
                .success(true)
                .message("Transaction created successfully")
                .data(transaction)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}