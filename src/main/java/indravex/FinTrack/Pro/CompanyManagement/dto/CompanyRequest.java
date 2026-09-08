package indravex.FinTrack.Pro.CompanyManagement.dto;


import indravex.FinTrack.Pro.CompanyManagement.entity.AccountType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CompanyRequest {

    @NotBlank(message = "Company name is required !!")
    private String companyName;

    private AccountType accountType;

    private String contactPerson;

    @Email(message = "Please enter valid email")
    @NotBlank(message = "Email is required")
    private String email;
}
