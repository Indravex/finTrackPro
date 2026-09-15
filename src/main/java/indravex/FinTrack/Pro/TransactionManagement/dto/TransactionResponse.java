package indravex.FinTrack.Pro.TransactionManagement.dto;

import indravex.FinTrack.Pro.TransactionManagement.entity.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {

    private Long transactionId;
    private TransactionType transactionType;
    private Long accountId;
    private Long companyId;
    private String companyName;
    private BigDecimal paymentAmount;
    private LocalDate date;
    private String paidTo;
    private String expenseCategory;
    private BigDecimal amount;
    private String remarkDescription;
}