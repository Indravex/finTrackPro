package indravex.FinTrack.Pro.TransactionManagement.repository;

import indravex.FinTrack.Pro.TransactionManagement.entity.Transaction;
import indravex.FinTrack.Pro.TransactionManagement.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    List<Transaction> findByDateAndTransactionType(
            LocalDate date,
            TransactionType transactionType
    );


}
