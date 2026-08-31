package indravex.FinTrack.Pro.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import indravex.FinTrack.Pro.entity.Transaction;
import indravex.FinTrack.Pro.enums.TransactionType;
import indravex.FinTrack.Pro.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TransactionService transactionService;

    @Test
    void createTransaction_shouldReturn201() throws Exception {

        Transaction transaction = Transaction.builder()
                .id(1L)
                .transactionType(TransactionType.DEBIT)
                .companyName("ABC Enterprises")
                .paymentAmount(new BigDecimal("15000.00"))
                .date(LocalDate.of(2026, 8, 27))
                .paidTo("XYZ Suppliers")
                .expenseCategory("Material Purchase")
                .amount(new BigDecimal("15000.00"))
                .remarkDescription("Material purchase payment")
                .build();

        when(transactionService.createTransaction(any()))
                .thenReturn(transaction);

        String requestJson = """
                {
                    "transactionType": "DEBIT",
                    "companyName": "ABC Enterprises",
                    "paymentAmount": 15000.00,
                    "date": "2026-08-27",
                    "paidTo": "XYZ Suppliers",
                    "expenseCategory": "Material Purchase",
                    "amount": 15000.00,
                    "remarkDescription": "Material purchase payment"
                }
                """;

        mockMvc.perform(
                        post("/api/v1/transactions")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void createTransaction_whenCompanyNameMissing_shouldReturn400() throws Exception {

        String requestJson = """
            {
                "transactionType": "DEBIT",
                "paymentAmount": 15000.00,
                "date": "2026-08-27",
                "paidTo": "XYZ Suppliers",
                "expenseCategory": "Material Purchase",
                "amount": 15000.00,
                "remarkDescription": "Material purchase payment"
            }
            """;

        mockMvc.perform(post("/api/v1/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createTransaction_whenAmountNegative_shouldReturn400() throws Exception {

        String requestJson = """
            {
                "transactionType": "DEBIT",
                "companyName": "ABC Enterprises",
                "paymentAmount": 15000.00,
                "date": "2026-08-27",
                "paidTo": "XYZ Suppliers",
                "expenseCategory": "Material Purchase",
                "amount": -15000.00,
                "remarkDescription": "Invalid transaction"
            }
            """;

        mockMvc.perform(post("/api/v1/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createTransaction_whenTransactionTypeInvalid_shouldReturn400() throws Exception {

        String requestJson = """
            {
                "transactionType": "INVALID",
                "companyName": "ABC Enterprises",
                "paymentAmount": 15000.00,
                "date": "2026-08-27",
                "paidTo": "XYZ Suppliers",
                "expenseCategory": "Material Purchase",
                "amount": 15000.00,
                "remarkDescription": "Invalid transaction"
            }
            """;

        mockMvc.perform(post("/api/v1/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }
}