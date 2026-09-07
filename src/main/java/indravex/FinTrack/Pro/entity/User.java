package indravex.FinTrack.Pro.entity;

import indravex.FinTrack.Pro.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password; // stored as BCrypt hash

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    public User(int getid, String name, String email, String encode, String upperCase) {
    }
}