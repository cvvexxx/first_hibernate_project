package cvv.project;

import cvv.project.entity.*;
import cvv.project.util.HibernateUtil;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.Instant;

class HibernateRunnerTest {

    @Test
    public void checkManyToMany() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = session.find(User.class, 1L);
            Chat chat = session.find(Chat.class, 2L);

            UserChat userChat = UserChat.builder()
                    .createdAt(Instant.now())
                    .createdBy("Artem")
                    .build();

            userChat.setUser(user);
            userChat.setChat(chat);

            session.persist(userChat);

            session.getTransaction().commit();
        }
    }

    @Test
    public void checkOneToOne() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            Profile profile = Profile.builder()
                    .language("RU")
                    .street("Russia")
                    .build();

            User user = User.builder()
                    .username("egor" + System.nanoTime() + "@gmail.com")
                    .build();

            session.persist(user);
            profile.setUser(user);
            session.persist(profile);

            session.getTransaction().commit();
        }
    }

    @Test
    public void testOrphanRemoval() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            Company company = session.find(Company.class, 1L);
            company.getUsers().removeIf(user -> user.getId().equals(2));

            session.getTransaction().commit();
        }
    }

    @Test
    public void checkOneToMany() {
        Company company = Company.builder().
                name("Yandex")
                .build();

        User user = User.builder()
                .username("Artem@gmail.com")
                .build();

        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            company.addUser(user);

            session.persist(company);

            session.getTransaction().commit();
        }
    }

}