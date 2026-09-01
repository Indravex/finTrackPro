package indravex.FinTrack.Pro.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "expense_categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name", length = 100, nullable = false)
    private String categoryName;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "category")
    private List<Transaction> transactions;
}