package net.webapp.springtest.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement // Enabling Annotation-Driven Transaction Management
@EnableJpaRepositories("net.webapp.springtest.repository") // Enable Spring Data JPA
@PropertySource("classpath:application.properties")
public class DataBaseConfig {

	// database property
	private static final String PROPERTY_NAME_DATABASE_DRIVER = "datasource.DriverClassName";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "datasource.Password";
	private static final String PROPERTY_NAME_DATABASE_URL = "datasource.Url";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "datasource.Username";
	// Hibernate property
	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	// scan for @Entity classes , no persistence xml
	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "net.webapp.springtest.models";

	@Resource
	private Environment env;

		// la source de données H2
		
	// @Bean(destroyMethod = "close") // needed when use with pool implmentation like hikari, to shutdown the data
	// source immediately when the spring application shuts down
	// This is not needed in the DriverManagerDataSource
	/**
	 * wouldn't recommend to use the DriverManagerDataSource in production because it isn't a connection pool (and hence
	 * no need for a close method as all connections are closed after usage!).
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		// HikariConfig dataSource = new HikariConfig();
		dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
		return dataSource;
	}
	
		// EntityManagerFactory


	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		em.setPackagesToScan(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN);
		em.setJpaVendorAdapter(jpaVendorAdaper());
		em.setJpaProperties(jpaProperties());
		em.afterPropertiesSet(); // to checknecessity
		return em.getObject();
	}

	private Properties jpaProperties() {
		// TODO Deplacer dans entitymanager
		Properties properties = new Properties();
		properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
//		properties.put("hibernate.validator.apply_to_ddl", env.getRequiredProperty("hibernate.validator.apply_to_ddl"));
//		properties.put("hibernate.validator.autoregister_listeners",env.getRequiredProperty("hibernate.validator.autoregister_listeners"));
//		properties.put("hibernate.dialect", env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
//		properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
//		properties.put("hibernate.use_sql_comments", env.getRequiredProperty("hibernate.use_sql_comments"));
//		properties.put("hibernate.show_sql", env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		return properties;
	}

		// Transaction manager

	@Bean
	public PlatformTransactionManager transactionManager() {
		/*
		 * JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		 * jpaTransactionManager.setEntityManagerFactory(entityManagerFactory()); return jpaTransactionManager;
		 */
		return new JpaTransactionManager(entityManagerFactory());
	}
	
	// le provider JPA
	// JPAVendorAdapter does seem to be the preferred approach
	 @Bean 
	 public JpaVendorAdapter jpaVendorAdaper() { 
		 final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter(); // vendorAdapter.setDatabase();
		 vendorAdapter.setDatabase(env.getRequiredProperty("jpa.database", Database.class));
		 vendorAdapter.setDatabasePlatform(env.getRequiredProperty("jpa.databasePlateform"));
		 vendorAdapter.setShowSql(env.getRequiredProperty("jpa.showSql", Boolean.class));
		 vendorAdapter.setGenerateDdl(env.getRequiredProperty("jpa.generateDdl", Boolean.class)); 
		 return vendorAdapter; 
	 }
	 

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	/*
	 * @Bean public TransactionTemplate transactionTemplate() { TransactionTemplate transactionTemplate = new
	 * TransactionTemplate(); transactionTemplate.setTransactionManager(transactionManager()); return
	 * transactionTemplate; }
	 */
	
	/*
	 * // TODO data source JNDI Depending active spring profile, lookup RDBMS DataSource from JNDI or from an embbeded
	 * H2 database. // Déclarer la datasource connexion comme une ressource JNDI
	 * 
	 * public JndiObjectFactoryBean dataSource() throws IllegalArgumentException { JndiObjectFactoryBean dataSource =
	 * new JndiObjectFactoryBean(); dataSource.setExpectedType(DataSource.class);
	 * dataSource.setJndiName("java:comp/env/jdbc/springtestdb"); // Déclarer un nom JNDI pour le chemin d’accès à un
	 * fichier de properties return dataSource; }
	 * 
	 * @Bean public DataSource dataSource() { final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
	 * dsLookup.setResourceRef(true); DataSource dataSource = dsLookup.getDataSource("java:comp/env/jdbc/springtestdb");
	 * return dataSource; }
	 * 
	 * @Bean
	 * 
	 * @Profile("test") public DataSource testDataSource() { return new
	 * EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build(); }
	 */

}
