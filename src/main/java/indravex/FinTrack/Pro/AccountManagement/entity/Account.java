package indravex.FinTrack.Pro.AccountManagement.entity;

import indravex.FinTrack.Pro.CompanyManagement.entity.AccountType;
import indravex.FinTrack.Pro.CompanyManagement.entity.Company;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id",nullable = false)
    private Company company;

    @Column(name = "account_name" , nullable = false)
    private String accountName;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "opening_balance",precision = 15,scale = 2)
    private BigDecimal openingBalance;

    @Column(name = "current_balance",precision = 15,scale = 2)
    private BigDecimal currentBalance;

    @Column(nullable = false)
    private boolean status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
