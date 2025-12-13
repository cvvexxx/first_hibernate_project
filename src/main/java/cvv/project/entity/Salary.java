package cvv.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Salary {
    @Id
    @Column(name = "user_id")
    private Long id;

    private Long salary;

    @OneToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Salary() {
    }

    public Salary(Long id, Long salary, User user) {
        this.id = id;
        this.salary = salary;
        this.user = user;
    }
}
