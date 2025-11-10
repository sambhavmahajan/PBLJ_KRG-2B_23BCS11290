package config;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

@Configuration
@ComponentScan(basePackages="com.example.app")
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public SessionFactory sessionFactory(){
        return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    @Bean
    public HibernateTransactionManager transactionManager(){
        return new HibernateTransactionManager(sessionFactory());
    }
}
