package cvv.project;


import cvv.project.convertor.BirthdayConvertor;
import cvv.project.entity.Birthday;
import cvv.project.entity.Role;
import cvv.project.entity.User;
import cvv.project.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class HibernateRunner {
    private static final Logger log = LoggerFactory.getLogger(HibernateRunner.class);

    public static void main(String[] args) {

        User user = User.builder()
                .username("artem100@mail.ru")
                .firstname("Artem")
                .lastname("Potapov")
                .birthdate(new Birthday(LocalDate.of(2007, 6, 23)))
                .role(Role.USER)
                .build();
        log.info("User object in transient state: {}", user);

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                user.setFirstname("Nikita");
                log.warn("User firstname was changed: {}", user.getFirstname());
//            session.persist(user);
//            session.merge(user);
//            session.remove(user);

                User user1 = session.find(User.class, "artem@mail.ru");
                User user2 = session.find(User.class, "artem1@mail.ru");
                User user3 = session.find(User.class, "artem100@mail.ru");

                session.getTransaction().commit();
            }
        } catch (Exception e) {
            log.error("Exception in HibernateRunner: ", e);
            throw e;
        }
    }
}