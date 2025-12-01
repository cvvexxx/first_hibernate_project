package cvv.project.entity;

import cvv.project.convertor.BirthdayConvertor;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@ToString(exclude = {"company", "profile"})
@EqualsAndHashCode(of = {"username", "profile"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    @Embedded
    private PersonalInfo personalInfo;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;
}



