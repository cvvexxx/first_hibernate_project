package cvv.project.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Profile {

    @Id
    @Column(name = "user_id")
    private Long id;


    private String street;
    private String language;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;

    public void setUser(User user) {
        this.user = user;
        this.id = user.getId();
        user.setProfile(this);
    }
}
