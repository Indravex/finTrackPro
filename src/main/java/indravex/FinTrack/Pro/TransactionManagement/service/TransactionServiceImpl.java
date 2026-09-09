package indravex.FinTrack.Pro.TransactionManagement.service;

import indravex.FinTrack.Pro.TransactionManagement.dto.TransactionRequest;
import indravex.FinTrack.Pro.TransactionManagement.entity.Transaction;
import indravex.FinTrack.Pro.TransactionManagement.repository.TransactionRepository;
import indravex.FinTrack.Pro.entity.Account;
import indravex.FinTrack.Pro.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    //private final AccountRepository accountRepository;

    @Override
    public Transaction createTransaction(TransactionRequest request) {

        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Account not found with id: " + request.getAccountId()));

        Transaction transaction = Transaction.builder()
                .account(account)
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
}