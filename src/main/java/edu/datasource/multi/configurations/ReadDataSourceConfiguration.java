package edu.datasource.multi.configurations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "edu.datasource",
        includeFilters = @ComponentScan.Filter(ReplicaDataSourceRepository.class),
        entityManagerFactoryRef = "replicaEntityManagerFactory"
)
public class ReadDataSourceConfiguration {

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.replicaUrl}")
    private String replicaUrl;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Bean
    public DataSource replicaDataSource() throws Exception {
        return DataSourceBuilder.create()
                .url(replicaUrl)
                .username(userName)
                .password(password)
                .driverClassName(driverClassName)
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean replicaEntityManagerFactory (
            EntityManagerFactoryBuilder builder, @Qualifier("replicaDataSource") DataSource replicaDataSource) {
        return builder
                .dataSource(replicaDataSource)
                .packages("edu.datasource.multi")
                .persistenceUnit("main")
                .build();
    }
}
