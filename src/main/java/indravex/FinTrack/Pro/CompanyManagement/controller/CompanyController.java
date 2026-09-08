package indravex.FinTrack.Pro.CompanyManagement.controller;

import indravex.FinTrack.Pro.utils.ApiResponse;
import indravex.FinTrack.Pro.CompanyManagement.dto.CompanyRequest;
import indravex.FinTrack.Pro.CompanyManagement.entity.Company;
import indravex.FinTrack.Pro.CompanyManagement.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Company>> createCompany(
            @Valid @RequestBody CompanyRequest request) {

        Company company = companyService.createCompany(request);

        ApiResponse<Company> response =
                new ApiResponse<>(true, "Company added successfully", company);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllCompanies()
    {
        return ResponseEntity.ok(companyService.getAllActiveCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable Long id){

        Company company = companyService.getCompanyById(id);

        return ResponseEntity.ok(company);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Void>> updateCompany(
            @PathVariable Long id,
            @Valid @RequestBody CompanyRequest request) {

        companyService.updateCompany(id, request);

        ApiResponse<Void> response =
                new ApiResponse<>(true, "Company name updated successfully", null);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse<Void>> deactivateCompany(
            @PathVariable Long id) {

        companyService.deactivateCompany(id);

        ApiResponse<Void> response =
                new ApiResponse<>(true, "Company deactivated successfully", null);

        return ResponseEntity.ok(response);
    }
}
