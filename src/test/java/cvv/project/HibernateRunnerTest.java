package cvv.project;

import cvv.project.entity.*;
import cvv.project.util.HibernateUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Cleanup;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class HibernateRunnerTest {

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

    @Test
    public void testHibernateApi() throws SQLException, IllegalAccessException {
    /*
        var user = User.builder()
                .username("Ivan1@mail.ru")
                .firstname("Ivan")
                .lastname("Ivanov")
                .birthdate(new Birthday(LocalDate.of(2007, 6, 23)))
                .build();

        String sql = """
                INSERT INTO
                %s
                (%s)
                VALUES
                (%s)
                """;

        var tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
                .map(table -> table.schema() + "." + table.name())
                .orElse(user.getClass().getName());

        Field[] fields = user.getClass().getDeclaredFields();


        String columnNames = Arrays.stream(fields)
                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName())
                ).collect(Collectors.joining(", "));

        var columnValues = Arrays.stream(fields)
                .map(field -> "?")
                .collect(Collectors.joining(", "));

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres"
                , "postgres"
                , "123");
        var statement = connection.prepareStatement(sql.formatted(tableName,
                columnNames,
                columnValues));

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            statement.setObject(1 +  i, fields[i].get(user));
        }


        statement.executeUpdate();
        statement.close();
        connection.close();
    */
    }

}