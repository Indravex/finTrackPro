package indravex.FinTrack.Pro.CompanyManagement.repository;

import indravex.FinTrack.Pro.CompanyManagement.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Long> {

    List<Company> findByStatusTrueOrderByCompanyNameAsc();

    Optional<Company> findByCompanyNameIgnoreCase(String companyName);

    Boolean existsByCompanyNameIgnoreCase(String companyName);
}
