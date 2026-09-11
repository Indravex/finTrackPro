package indravex.FinTrack.Pro.TransactionManagement.service;

import indravex.FinTrack.Pro.TransactionManagement.dto.TransactionRequest;
import indravex.FinTrack.Pro.TransactionManagement.entity.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    Transaction createTransaction(TransactionRequest request);

    List<Transaction> getTransactions(
            Long companyId,
            Long accountId,
            LocalDate fromDate,
            LocalDate toDate
    );
}
