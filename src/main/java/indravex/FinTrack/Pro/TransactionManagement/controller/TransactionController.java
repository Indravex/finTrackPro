package indravex.FinTrack.Pro.TransactionManagement.controller;

import indravex.FinTrack.Pro.TransactionManagement.dto.TransactionRequest;
import indravex.FinTrack.Pro.TransactionManagement.dto.TransactionResponse;
import indravex.FinTrack.Pro.TransactionManagement.entity.Transaction;
import indravex.FinTrack.Pro.TransactionManagement.service.TransactionService;
import indravex.FinTrack.Pro.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<ApiResponse<TransactionResponse>> createTransaction(
            @Valid @RequestBody TransactionRequest request) {

        Transaction transaction = transactionService.createTransaction(request);

        TransactionResponse transactionResponse = TransactionResponse.builder()
                .transactionId(transaction.getId())
                .transactionType(transaction.getTransactionType())
                .accountId(transaction.getAccount().getId())
                .companyId(transaction.getAccount().getCompany().getId())
                .companyName(transaction.getCompanyName())
                .paymentAmount(transaction.getPaymentAmount())
                .date(transaction.getDate())
                .paidTo(transaction.getPaidTo())
                .expenseCategory(transaction.getExpenseCategory())
                .amount(transaction.getAmount())
                .remarkDescription(transaction.getRemarkDescription())
                .build();

        ApiResponse<TransactionResponse> response =
                new ApiResponse<>(
                        true,
                        "Transaction created successfully",
                        transactionResponse
                );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}