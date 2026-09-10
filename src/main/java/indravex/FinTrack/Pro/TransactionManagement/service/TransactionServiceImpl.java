package indravex.FinTrack.Pro.TransactionManagement.service;

import indravex.FinTrack.Pro.CompanyManagement.entity.Company;
import indravex.FinTrack.Pro.CompanyManagement.repository.CompanyRepository;
import indravex.FinTrack.Pro.TransactionManagement.dto.TransactionRequest;
import indravex.FinTrack.Pro.TransactionManagement.entity.Transaction;
import indravex.FinTrack.Pro.TransactionManagement.entity.TransactionType;
import indravex.FinTrack.Pro.TransactionManagement.repository.TransactionRepository;
import indravex.FinTrack.Pro.entity.Account;
import indravex.FinTrack.Pro.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CompanyRepository companyRepository;

    @Override
    public Transaction createTransaction(TransactionRequest request) {

        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Account not found with id: " + request.getAccountId()));

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Company not found with id: " + request.getCompanyId()));

        if (!account.getCompany().getId().equals(company.getId())) {
            throw new IllegalArgumentException(
                    "Account does not belong to the selected company");
        }

        BigDecimal currentBalance = account.getCurrentBalance() != null
                ? account.getCurrentBalance()
                : BigDecimal.ZERO;

        BigDecimal newBalance;

        if (request.getTransactionType() == TransactionType.CREDIT) {
            newBalance = currentBalance.add(request.getAmount());
        } else {
            newBalance = currentBalance.subtract(request.getAmount());
        }

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

        transactionRepository.save(transaction);
        account.setCurrentBalance(newBalance);
        accountRepository.save(account);
        return transaction;
    }
}