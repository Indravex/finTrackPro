package indravex.FinTrack.Pro.AccountManagement.dto;

import indravex.FinTrack.Pro.CompanyManagement.entity.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRequest {

    @NotNull(message = "Company ID is required")
    private Long companyId;

    @NotBlank(message = "Account name is required")
    private String accountName;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    @NotNull(message = "Opening balance is required")
    @DecimalMin(value = "0.0", inclusive = true,
            message = "Opening balance must not be negative")
    private BigDecimal openingBalance;
}