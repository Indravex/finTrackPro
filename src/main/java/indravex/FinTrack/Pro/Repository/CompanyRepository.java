package indravex.FinTrack.Pro.Repository;

import indravex.FinTrack.Pro.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Long> {

    List<Company> findByStatusTrueOrderByCompanyNameAsc();

    Optional<Company> findByCompanyNameIgnoreCase(String companyName);

    Boolean existsByCompanyNameIgnoreCase(String companyName);
}
