package indravex.FinTrack.Pro.DashboardManagement.service;

import indravex.FinTrack.Pro.DashboardManagement.dto.AccountSummary;
import indravex.FinTrack.Pro.DashboardManagement.dto.DashboardResponse;
import indravex.FinTrack.Pro.DashboardManagement.dto.RecentTransactionResponse;
import indravex.FinTrack.Pro.TransactionManagement.entity.Transaction;
import indravex.FinTrack.Pro.TransactionManagement.entity.TransactionType;
import indravex.FinTrack.Pro.AccountManagement.entity.Account;
import indravex.FinTrack.Pro.AccountManagement.repository.AccountRepository;
import indravex.FinTrack.Pro.TransactionManagement.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public DashboardResponse getDashboard(Long companyId) {

        List<Account> accountList = companyId == null
                ? accountRepository.findAll()
                : accountRepository.findByCompanyId(companyId);

        BigDecimal totalBalance = accountList
                .stream()
                .map(Account::getCurrentBalance)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        LocalDate today = LocalDate.now();

        BigDecimal todayCredit = transactionRepository
                .findByDateAndTransactionType(today, TransactionType.CREDIT)
                .stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal todayDebit = transactionRepository
                .findByDateAndTransactionType(today, TransactionType.DEBIT)
                .stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<AccountSummary> accounts = accountRepository.findAll()
                .stream()
                .map(account -> AccountSummary.builder()
                        .accountId(account.getId())
                        .accountName(account.getAccountName())
                        .accountType(account.getAccountType().name())
                        .currentBalance(account.getCurrentBalance())
                        .build())
                .toList();
        List<RecentTransactionResponse> recentTransactions = transactionRepository.findAll()
                .stream()
                .sorted((t1, t2) -> t2.getDate().compareTo(t1.getDate()))
                .limit(10)
                .map(transaction -> RecentTransactionResponse.builder()
                        .transactionId(transaction.getId())
                        .date(transaction.getDate())
                        .partyName(transaction.getPaidTo())
                        .accountName(transaction.getAccount().getAccountName())
                        .transactionType(transaction.getTransactionType().name())
                        .amount(transaction.getAmount())
                        .build())
                .toList();

        return DashboardResponse.builder()
                .totalBalance(totalBalance)
                .todayCredit(todayCredit)
                .todayDebit(todayDebit)
                .accounts(accounts)
                .recentTransactions(recentTransactions)
                .build();
    }
}