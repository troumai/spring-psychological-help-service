//package kz.iitu.itse1903.abimoldayeva;
//
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.activation.DataSource;
//import java.util.logging.Logger;
//
//
//@Configuration
//@PropertySource("classpath:application.properties")
//@EnableTransactionManagement
//public class HibernateConf {
//
//    private static Logger logger =
//            (Logger) LoggerFactory.getLogger(HibernateConf.class);
//    @Value("${driverClassName}")
//    private String driverClassName;
//    @Value("${url}")
//    private String url;
//    @Value("${username}")
//    private String username;
//    @Value("${password}")
//    private String password;
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer
//    propertySourcesPlaceholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }
//    @Bean(destroyMethod = "close")
//    public DataSource dataSource() {
//        try {
//            BasicDataSource dataSource = new BasicDataSource();
//            dataSource.setDriverClassName(driverClassName);
//            dataSource.setUrl(url);
//            dataSource.setUsername(username);
//            dataSource.setPassword(password);
//            return dataSource;
//        } catch (Exception e) {
//            logger.error("DBCP DataSource bean cannot be created!", e);
//            return null;
//        }
//    }
//    private Properties hibernateProperties() {
//        Properties hibernateProp = new Properties();
//        hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//        hibernateProp.put("hibernate.hbm2ddl.auto", "create-drop");
//        hibernateProp.put("hibernate.format_sql", true);
//        hibernateProp.put("hibernate.use_sql_comments", true);
//        hibernateProp.put("hibernate.show_sql", true);
//        hibernateProp.put("hibernate.max_fetch_depth", 3);
//        hibernateProp.put("hibernate.jdbc.batch_size", 10);
//        hibernateProp.put("hibernate.jdbc.fetch_size", 50);
//        return hibernateProp;
//    }
//    @Bean
//    public SessionFactory sessionFactory() {
//        return new LocalSessionFactoryBuilder(dataSource())
//                .scanPackages("com.apress.prospring5.ch7.entities")
//                .addProperties(hibernateProperties())
//                .buildSessionFactory();
//    }
//}
