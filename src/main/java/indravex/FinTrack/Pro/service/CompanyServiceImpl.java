package indravex.FinTrack.Pro.service;

import indravex.FinTrack.Pro.Repository.CompanyRepository;
import indravex.FinTrack.Pro.dto.CompanyRequest;
import indravex.FinTrack.Pro.entity.Company;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;

    @Override
    public Company createCompany(CompanyRequest request){

        if(companyRepository.existsByCompanyNameIgnoreCase(request.getCompanyName())){
            throw new RuntimeException("Comapny already exists !!");
        }

        Company company = Company.builder()
                .companyName(request.getCompanyName())
                .status(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return companyRepository.save(company);
    }
    @Override
    public List<Company> getAllActiveCompanies(){

        return companyRepository.findByStatusTrueOrderByCompanyNameAsc();
    }

    @Override
    public Company getCompanyById(Long id){

        return companyRepository.findById(id).orElseThrow(()-> new RuntimeException("Company not found with id : "+id));
    }

    @Override
    public Company updateCompany(Long id , CompanyRequest request){

        Company company = companyRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Company nt found with id :"+id));

        if(!company.isStatus()){
            throw new RuntimeException("Inactive company can not be updated");
        }

        if(companyRepository.existsByCompanyNameIgnoreCase(request.getCompanyName())){
            throw new RuntimeException("Company alraedy exists ");
        }

        company.setCompanyName(request.getCompanyName());
        company.setUpdatedAt(LocalDateTime.now());
        return companyRepository.save(company);
    }

    @Override
    public void deactivateCompany(Long id){

        Company company = companyRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Company not found with id : "+id));

        company.setStatus(false);
        company.setUpdatedAt(LocalDateTime.now());

        companyRepository.save(company);
    }
}
