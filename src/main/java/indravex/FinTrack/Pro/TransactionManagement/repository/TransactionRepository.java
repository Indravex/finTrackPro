package indravex.FinTrack.Pro.TransactionManagement.repository;

import indravex.FinTrack.Pro.TransactionManagement.entity.Transaction;
import indravex.FinTrack.Pro.TransactionManagement.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByDateAndTransactionType(
            LocalDate date,
            TransactionType transactionType
    );

    List<Transaction> findByAccountId(Long accountId);

    List<Transaction> findByAccountCompanyId(Long companyId);

    List<Transaction> findByDateBetween(
            LocalDate fromDate,
            LocalDate toDate
    );

    List<Transaction> findByAccountCompanyIdAndAccountId(
            Long companyId,
            Long accountId
    );

    List<Transaction> findByAccountCompanyIdAndDateBetween(
            Long companyId,
            LocalDate fromDate,
            LocalDate toDate
    );

    List<Transaction> findByAccountIdAndDateBetween(
            Long accountId,
            LocalDate fromDate,
            LocalDate toDate
    );

    List<Transaction> findByAccountCompanyIdAndAccountIdAndDateBetween(
            Long companyId,
            Long accountId,
            LocalDate fromDate,
            LocalDate toDate
    );
}