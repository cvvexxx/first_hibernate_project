package cvv.project.UserDao;


import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import cvv.project.entity.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

import static cvv.project.entity.QCompany.company;
import static cvv.project.entity.QSalary.salary1;
import static cvv.project.entity.QUser.user;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {

    private static UserDao INSTANCE = new UserDao();

    public static UserDao getInstance() {
        return INSTANCE;
    }

    public List<User> findAll(Session session) {
        return new JPAQuery<User>(session).select(user).from(user).fetch();
    }

    public List<User> findByFirstName(Session session, String firstName) {
        return new JPAQuery<User>(session).select(user).from(user)
                .where(user.personalInfo.firstname.eq(firstName))
                .fetch();
    }

    public List<User> findCompanyByName(Session session, String companyName) {
        return new JPAQuery<User>(session).select(user).from(company)
                .join(company.users, user)
                .where(company.name.eq(companyName))
                .fetch();
    }

    public List<User> findLimitedUsersOrderedByBirthday(Session session, int limit) {
        return new JPAQuery<User>(session).select(user).from(user)
                .limit(limit)
                .orderBy(new OrderSpecifier(Order.ASC, user.personalInfo.birthdate))
                .fetch();
    }

    public Long findSalaryAmountByFirstName(Session session, String firstName) {
        return new JPAQuery<Double>(session)
                .select(salary1.salary)
                .from(salary1)
                .join(salary1.user, user)
                .where(user.personalInfo.firstname.eq(firstName))
                .fetchOne();
    }

    public List<Tuple> findCompanyNameAndAvgSalaryAmountByCompanyId(Session session, Integer companyId) {
        return new JPAQuery<Tuple>(session)
                .select(company.name, salary1.salary.avg())
                .from(salary1)
                .join(salary1.user, user)
                .join(user.company, company)
                .where(company.id.eq(companyId))
                .groupBy(company.name)
                .orderBy(company.name.asc())
                .fetch();
    }

    public List<Tuple> test(Session session) {
        return new JPAQuery<Tuple>(session)
                .select(user, salary1.salary.avg())
                .from(salary1)
                .groupBy(user.id)
                .having(salary1.salary.gt(
                        new JPAQuery<Double>(session)
                                .select(salary1.salary.avg())
                                .from(salary1)
                                .fetchOne()
                ))
                .orderBy(user.personalInfo.firstname.asc())
                .fetch();
    }
}
