package cvv.project;


import cvv.project.convertor.BirthdayConvertor;
import cvv.project.entity.Birthday;
import cvv.project.entity.Role;
import cvv.project.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(User.class);
        configuration.addAttributeConverter(new BirthdayConvertor(), true);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = User.builder()
                    .username("artem100@mail.ru")
                    .firstname("Artem")
                    .lastname("Potapov")
                    .birthdate(new Birthday(LocalDate.of(2007, 6, 23)))
                    .role(Role.USER)
                    .build();

//            session.persist(user);
//            session.merge(user);
//            session.remove(user);

            User user1 = session.find(User.class, "artem@mail.ru");
            User user2 = session.find(User.class, "artem1@mail.ru");
            User user3 = session.find(User.class, "artem100@mail.ru");

            session.getTransaction().commit();
        }
    }
}