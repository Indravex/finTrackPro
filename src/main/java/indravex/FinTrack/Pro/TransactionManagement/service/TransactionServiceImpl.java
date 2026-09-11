package indravex.FinTrack.Pro.TransactionManagement.service;

import indravex.FinTrack.Pro.TransactionManagement.dto.TransactionRequest;
import indravex.FinTrack.Pro.TransactionManagement.entity.Transaction;
import indravex.FinTrack.Pro.TransactionManagement.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public Transaction createTransaction(TransactionRequest request) {

        Transaction transaction = Transaction.builder()
                .transactionType(request.getTransactionType())
                .companyName(request.getCompanyName())
                .paymentAmount(request.getPaymentAmount())
                .date(request.getDate())
                .paidTo(request.getPaidTo())
                .expenseCategory(request.getExpenseCategory())
                .amount(request.getAmount())
                .remarkDescription(request.getRemarkDescription())
                .createdAt(java.time.LocalDateTime.now())
                .updatedAt(java.time.LocalDateTime.now())
                .build();

        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactions(
            Long companyId,
            Long accountId,
            LocalDate fromDate,
            LocalDate toDate) {

        if (companyId != null && accountId != null && fromDate != null && toDate != null) {
            return transactionRepository
                    .findByAccountCompanyIdAndAccountIdAndDateBetween(
                            companyId, accountId, fromDate, toDate);
        }

        if (companyId != null && accountId != null) {
            return transactionRepository
                    .findByAccountCompanyIdAndAccountId(companyId, accountId);
        }

        if (companyId != null && fromDate != null && toDate != null) {
            return transactionRepository
                    .findByAccountCompanyIdAndDateBetween(
                            companyId, fromDate, toDate);
        }

        if (accountId != null && fromDate != null && toDate != null) {
            return transactionRepository
                    .findByAccountIdAndDateBetween(
                            accountId, fromDate, toDate);
        }

        if (companyId != null) {
            return transactionRepository.findByAccountCompanyId(companyId);
        }

        if (accountId != null) {
            return transactionRepository.findByAccountId(accountId);
        }

        if (fromDate != null && toDate != null) {
            return transactionRepository.findByDateBetween(fromDate, toDate);
        }

        return transactionRepository.findAll();
    }
}