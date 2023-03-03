package lkwoung.movie.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "entityManagerFactory",
    basePackages = {"lkwoung.movie.entity"},
    transactionManagerRef = "transactionManager"
)
public class DataSourceConfig {

    @Primary
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        HikariConfig dataSource = new HikariConfig();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/movie_community?characterEncoding=UTF-8&serverTimezone=Asia/Seoul");
        dataSource.setUsername("root");
        dataSource.setPassword("root3306");

        return new HikariDataSource(dataSource);
    }

}
