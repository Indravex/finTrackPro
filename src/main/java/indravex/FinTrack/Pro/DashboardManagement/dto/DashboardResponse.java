package indravex.FinTrack.Pro.DashboardManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponse {

    private BigDecimal totalBalance;
    private BigDecimal todayCredit;
    private BigDecimal todayDebit;

    private List<AccountSummary> accounts;
    private List<RecentTransactionResponse> recentTransactions;
}