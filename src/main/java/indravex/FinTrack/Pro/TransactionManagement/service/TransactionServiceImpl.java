package indravex.FinTrack.Pro.TransactionManagement.service;

import indravex.FinTrack.Pro.TransactionManagement.dto.TransactionRequest;
import indravex.FinTrack.Pro.TransactionManagement.entity.Transaction;
import indravex.FinTrack.Pro.TransactionManagement.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}