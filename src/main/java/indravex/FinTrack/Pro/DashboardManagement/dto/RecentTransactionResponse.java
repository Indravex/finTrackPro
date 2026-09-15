package indravex.FinTrack.Pro.DashboardManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecentTransactionResponse {

    private Long transactionId;
    private LocalDate date;
    private String partyName;
    private String accountName;
    private String transactionType;
    private BigDecimal amount;
}