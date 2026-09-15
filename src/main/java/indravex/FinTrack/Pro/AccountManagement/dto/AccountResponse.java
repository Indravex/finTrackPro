package indravex.FinTrack.Pro.AccountManagement.dto;

import indravex.FinTrack.Pro.CompanyManagement.entity.AccountType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponse {

    private Long accountId;
    private Long companyId;
    private String accountName;
    private AccountType accountType;
    private BigDecimal openingBalance;
    private BigDecimal currentBalance;
    private boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}