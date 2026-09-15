package indravex.FinTrack.Pro.DashboardManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountSummary {

    private Long accountId;
    private String accountName;
    private String accountType;
    private BigDecimal currentBalance;
}