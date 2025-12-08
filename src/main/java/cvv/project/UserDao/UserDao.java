package cvv.project.UserDao;


import cvv.project.entity.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {

    private static UserDao INSTANCE = new UserDao();

    public static UserDao getInstance() {
        return INSTANCE;
    }

    public List<User> findAll(Session session) {
        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(User.class);
        var user = criteria.from(User.class);

        criteria.select(user);

        return session.createQuery(criteria).list();
    }

    public List<User> findByFirstName(Session session, String firstName) {
        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(User.class);
        var user = criteria.from(User.class);

        criteria.select(user).where(cb.equal(user.get(User_.personalInfo).get(PersonalInfo_.firstname), firstName));

        return session.createQuery(criteria).list();
    }

    public List<Company> findCompanyByName(Session session, String companyName) {
        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(Company.class);
        var company = criteria.from(Company.class);

        criteria.select(company).where(cb.equal(company.get(Company_.name), companyName));

        return session.createQuery(criteria).list();
    }
}
