package cvv.project.util;

import cvv.project.convertor.BirthdayConvertor;
import cvv.project.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(User.class);
        configuration.addAttributeConverter(new BirthdayConvertor(), true);
        return configuration.buildSessionFactory();
    }
}
