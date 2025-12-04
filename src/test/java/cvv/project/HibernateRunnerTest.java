package cvv.project;

import cvv.project.entity.*;
import cvv.project.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;

class HibernateRunnerTest {
    private static SessionFactory sessionFactory;

    @BeforeAll
    static void setUp() {
        sessionFactory = HibernateUtil.buildSessionFactory();
    }

    @AfterAll
    static void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    public void createUsers() {
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

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.persist(user1);
            session.persist(user2);
            session.persist(user3);
            session.persist(user4);
            session.persist(user5);

            session.getTransaction().commit();
        }
    }

    @Test
    public void checkHQL() {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            String name = "John";
            var users = session.createQuery("""
                            select u from User u
                            where u.personalInfo.firstname = :firstname
                            """)
                    .setParameter("firstname", name)
                    .list();
            System.out.println(users);

            session.getTransaction().commit();
        }
    }
}
//    @Test
//    public void checkManyToMany() {
//        try (var sessionFactory = HibernateUtil.buildSessionFactory();
//             var session = sessionFactory.openSession()) {
//            session.beginTransaction();
//
//            User user = session.find(User.class, 1L);
//            Chat chat = session.find(Chat.class, 2L);
//
//            UserChat userChat = UserChat.builder()
//                    .createdAt(Instant.now())
//                    .createdBy("Artem")
//                    .build();
//
//            userChat.setUser(user);
//            userChat.setChat(chat);
//
//            session.persist(userChat);
//
//            session.getTransaction().commit();
//        }
//    }
//
//    @Test
//    public void checkOneToOne() {
//        try (var sessionFactory = HibernateUtil.buildSessionFactory();
//             var session = sessionFactory.openSession()) {
//            session.beginTransaction();
//
//            Profile profile = Profile.builder()
//                    .language("RU")
//                    .street("Russia")
//                    .build();
//
//            User user = User.builder()
//                    .username("egor" + System.nanoTime() + "@gmail.com")
//                    .build();
//
//            session.persist(user);
//            profile.setUser(user);
//            session.persist(profile);
//
//            session.getTransaction().commit();
//        }
//    }
//
//    @Test
//    public void testOrphanRemoval() {
//        try (var sessionFactory = HibernateUtil.buildSessionFactory();
//             var session = sessionFactory.openSession()) {
//            session.beginTransaction();
//
//            Company company = session.find(Company.class, 1L);
//            company.getUsers().removeIf(user -> user.getId().equals(2));
//
//            session.getTransaction().commit();
//        }
//    }
//
//    @Test
//    public void checkOneToMany() {
//        Company company = Company.builder().
//                name("Yandex")
//                .build();
//
//        User user = User.builder()
//                .username("Artem@gmail.com")
//                .build();
//
//        try (var sessionFactory = HibernateUtil.buildSessionFactory();
//             var session = sessionFactory.openSession()) {
//            session.beginTransaction();
//
//            company.addUser(user);
//
//            session.persist(company);
//
//            session.getTransaction().commit();
//        }
//    }
