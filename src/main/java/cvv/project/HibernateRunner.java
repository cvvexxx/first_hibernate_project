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
//
//        Company company = Company.builder().
//                name("LL")
//                .build();

//        User user = User.builder()
//                .username("Artem.potapov12333@gmail.com")
//                .personalInfo(PersonalInfo.builder()
//                        .firstname("Artem")
//                        .lastname("Potapov")
//                        .birthdate(new Birthday(LocalDate.of(2007, 6, 23)))
//                        .build())
//                .role(Role.ADMIN)
//                .company(company)
//                .build();
//        log.info("User object in transient state: {}", user);

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();

//                session.persist(company);
//                var user1 = session.find(User.class, 2);
//                user1.setCompany(company);
//                session.merge(user1);

                session.getTransaction().commit();
            }
        } catch (Exception e) {
            log.error("Exception in HibernateRunner: ", e);
            throw e;
        }
    }
}