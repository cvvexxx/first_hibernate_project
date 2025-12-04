package cvv.project;


import cvv.project.convertor.BirthdayConvertor;
import cvv.project.entity.*;
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
        User user1 = User.builder()
                .username("john_doe")
                .personalInfo(new PersonalInfo(
                        "John",
                        "Doe",
                        new Birthday(LocalDate.of(1995, 3, 15))
                ))
                .role(Role.ADMIN)
                .build();

        User user2 = User.builder()
                .username("jane_smith")
                .personalInfo(new PersonalInfo(
                        "Jane",
                        "Smith",
                        new Birthday(LocalDate.of(1998, 7, 2))
                ))
                .role(Role.USER)
                .build();

        User user3 = User.builder()
                .username("alex_ivanov")
                .personalInfo(new PersonalInfo(
                        "Alex",
                        "Ivanov",
                        new Birthday(LocalDate.of(2000, 1, 20))
                ))
                .role(Role.USER)
                .build();

        User user4 = User.builder()
                .username("maria_petrov")
                .personalInfo(new PersonalInfo(
                        "Maria",
                        "Petrova",
                        new Birthday(LocalDate.of(1993, 11, 5))
                        ))
                .role(Role.ADMIN)
                .build();

        User user5 = User.builder()
                .username("sergey_kuznetsov")
                .personalInfo(new PersonalInfo(
                        "Sergey",
                        "Kuznetsov",
                        new Birthday(LocalDate.of(1999, 9, 9))
                        ))
                .role(Role.USER)
                .build();

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                session.persist(user1);
                session.persist(user2);
                session.persist(user3);
                session.persist(user4);
                session.persist(user5);

                session.getTransaction().commit();
            }
        } catch (Exception e) {
            log.error("Exception in HibernateRunner: ", e);
            throw e;
        }
    }
}