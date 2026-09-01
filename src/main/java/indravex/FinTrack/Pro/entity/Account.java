package indravex.FinTrack.Pro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private String accountType;

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

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;
}
