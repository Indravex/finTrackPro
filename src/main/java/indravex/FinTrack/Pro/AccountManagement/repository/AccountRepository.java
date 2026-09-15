package indravex.FinTrack.Pro.AccountManagement.repository;

import indravex.FinTrack.Pro.AccountManagement.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByCompanyId(Long companyId);
}
