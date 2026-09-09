package indravex.FinTrack.Pro.repository;

import indravex.FinTrack.Pro.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByCompanyId(Long companyId);
}
