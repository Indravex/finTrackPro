package indravex.FinTrack.Pro.TransactionManagement.controller;

import indravex.FinTrack.Pro.TransactionManagement.dto.TransactionResponse;
import indravex.FinTrack.Pro.TransactionManagement.entity.Transaction;
import indravex.FinTrack.Pro.TransactionManagement.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionFilterController {

    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getTransactions(
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) Long accountId,
            @RequestParam(required = false) LocalDate fromDate,
            @RequestParam(required = false) LocalDate toDate) {

        List<Transaction> transactions = transactionService.getTransactions(
                companyId, accountId, fromDate, toDate);

        List<TransactionResponse> responses = transactions.stream()
                .map(transaction -> TransactionResponse.builder()
                        .transactionId(transaction.getId())
                        .transactionType(transaction.getTransactionType())
                        .companyName(transaction.getCompanyName())
                        .paymentAmount(transaction.getPaymentAmount())
                        .date(transaction.getDate())
                        .paidTo(transaction.getPaidTo())
                        .expenseCategory(transaction.getExpenseCategory())
                        .amount(transaction.getAmount())
                        .remarkDescription(transaction.getRemarkDescription())
                        .build())
                .toList();

        return ResponseEntity.ok(responses);
    }
}