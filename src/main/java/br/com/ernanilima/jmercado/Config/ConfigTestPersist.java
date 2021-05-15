package br.com.ernanilima.jmercado.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        // isso pode ser usado de outra forma como demonstrado no perfil dev
        basePackages = "br.com.ernanilima.jmercado.repository",
        entityManagerFactoryRef = "emfTest",
        transactionManagerRef = "tmTest"
)
@Profile("test")
public class ConfigTestPersist {

    @Bean
    public LocalContainerEntityManagerFactoryBean emfTest() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(bancoDeDados());
        em.setPackagesToScan(this.getModelsParaEscanear());

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(getPropriedadesDoBancoDeDados());
        return em;
    }

    @Bean
    public DataSource bancoDeDados() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:file:./jmercado");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    private Properties getPropriedadesDoBancoDeDados() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.connection.charSet", "UTF-8");
        return properties;
    }

    private String[] getModelsParaEscanear() {
        // importante para a geracao do .jar
        // evita o uso da anotacao @ComponentScan(...)
        ArrayList<String> packages = new ArrayList<>();
        packages.add("br.com.ernanilima.jmercado.model");
        return packages.toArray(new String[packages.size()]);
    }

    @Bean
    public PlatformTransactionManager tmTest(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emfTest().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor et(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
