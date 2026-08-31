package indravex.FinTrack.Pro.dto;

import indravex.FinTrack.Pro.enums.TransactionType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionRequest {

    @NotNull(message = "Transaction type is required")
    private TransactionType transactionType;

    @NotBlank(message = "Company name is required")
    @Size(max = 100, message = "Company name must not exceed 100 characters")
    private String companyName;

    @NotNull(message = "Payment amount is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Payment amount must not be negative")
    private BigDecimal paymentAmount;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotBlank(message = "Paid to is required")
    @Size(max = 100, message = "Paid to must not exceed 100 characters")
    private String paidTo;

    @NotBlank(message = "Expense category is required")
    @Size(max = 100, message = "Expense category must not exceed 100 characters")
    private String expenseCategory;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Amount must not be negative")
    private BigDecimal amount;

    @Size(max = 500, message = "Remark description must not exceed 500 characters")
    private String remarkDescription;


    public TransactionRequest() {
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPaidTo() {
        return paidTo;
    }

    public void setPaidTo(String paidTo) {
        this.paidTo = paidTo;
    }

    public String getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(String expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRemarkDescription() {
        return remarkDescription;
    }

    public void setRemarkDescription(String remarkDescription) {
        this.remarkDescription = remarkDescription;
    }
}