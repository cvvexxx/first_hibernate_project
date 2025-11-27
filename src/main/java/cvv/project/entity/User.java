package cvv.project.entity;

import cvv.project.convertor.BirthdayConvertor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    private String username;
    private String firstname;
    private String lastname;
    @Convert(converter = BirthdayConvertor.class)
    @Column(name = "birth_date")
    private Birthday birthdate;
    @Enumerated(EnumType.STRING)
    private Role role;
}
