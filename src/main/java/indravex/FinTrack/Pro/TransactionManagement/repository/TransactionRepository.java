package indravex.FinTrack.Pro.TransactionManagement.repository;

import indravex.FinTrack.Pro.TransactionManagement.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

}
