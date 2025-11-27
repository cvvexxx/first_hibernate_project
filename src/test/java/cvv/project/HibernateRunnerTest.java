package cvv.project;

import cvv.project.entity.Birthday;
import cvv.project.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
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