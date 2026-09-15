package indravex.FinTrack.Pro.TransactionManagement.dto;

import indravex.FinTrack.Pro.TransactionManagement.entity.TransactionType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRequest {

    @NotNull(message = "Transaction type is required")
    private TransactionType transactionType;

    @NotNull(message = "Account ID is required")
    private Long accountId;

    @NotNull(message = "Company ID is required")
    private Long companyId;

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotNull(message = "Payment amount is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Payment amount must not be negative")
    private BigDecimal paymentAmount;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotBlank(message = "Paid to is required")
    private String paidTo;

    @NotBlank(message = "Expense category is required")
    private String expenseCategory;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Amount must not be negative")
    private BigDecimal amount;

    @Size(max = 500, message = "Remark description must not exceed 500 characters")
    private String remarkDescription;
}