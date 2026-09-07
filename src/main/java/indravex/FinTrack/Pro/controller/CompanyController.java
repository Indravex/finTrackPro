package indravex.FinTrack.Pro.controller;

import indravex.FinTrack.Pro.dto.ApiResponse;
import indravex.FinTrack.Pro.dto.CompanyRequest;
import indravex.FinTrack.Pro.entity.Company;
import indravex.FinTrack.Pro.service.CompanyService;
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
    public ResponseEntity<?> createCompany(@Valid
                                           @RequestBody CompanyRequest request){

        Company company = companyService.createCompany(request);

        ApiResponse response = ApiResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Company added successfully")
                .build();

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
    public ResponseEntity<ApiResponse> updateCompany(@PathVariable Long id ,@Valid @RequestBody CompanyRequest request){

        Company company = companyService.updateCompany(id,request);

        ApiResponse response = ApiResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Company name Updated successfully ")
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse> deactivateCompany(@PathVariable Long id){

        companyService.deactivateCompany(id);
        ApiResponse response = ApiResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Company deactivated successfully")
                .build();

        return ResponseEntity.ok(response);
    }
}
