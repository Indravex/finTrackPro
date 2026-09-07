package indravex.FinTrack.Pro.service;

import indravex.FinTrack.Pro.dto.CompanyRequest;
import indravex.FinTrack.Pro.entity.Company;
import jakarta.validation.Valid;

import java.util.List;

public interface CompanyService {
    List<Company> getAllActiveCompanies();

    Company createCompany(@Valid CompanyRequest request);

    Company getCompanyById(Long id);

    Company updateCompany(Long id , @Valid CompanyRequest request);

    void deactivateCompany(Long id);
}
