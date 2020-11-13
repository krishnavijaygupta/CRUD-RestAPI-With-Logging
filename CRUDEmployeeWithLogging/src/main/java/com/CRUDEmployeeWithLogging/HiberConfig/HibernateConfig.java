package com.CRUDEmployeeWithLogging.HiberConfig;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.CRUDEmployeeWithLogging.Model")
@EnableTransactionManagement
@PropertySource(value= {"classpath:application.properties"})

/**
 * @author Sonu Kumar Gupta
 *
 */
public class HibernateConfig {
	
	/** Creating Bean For Resource Packege And Classes
	 * @return
	 */
	@Bean
	public LocalSessionFactoryBean getSessionFactory(){
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setPackagesToScan(new String[]{"com.CRUDEmployeeWithLogging.Model"});
		sessionFactory.setHibernateProperties(getHibernateProperties());
		return sessionFactory;
	}
	
	/** Connection Pooling With MySql Through hibernate DataSource
	 * @return
	 */
	@Bean
	public DataSource getDataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	dataSource.setUrl("jdbc:mysql://localhost:3306/employees");
	dataSource.setUsername("root");
	dataSource.setPassword("root");
	
		return dataSource;								
	}
	
	/** Put all Configuration From Application Properties
	 * @return
	 */
	private Properties getHibernateProperties(){
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		Properties properties = new Properties();
		properties.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
		properties.put("hibernate.show_sql","true");
		properties.put("hibernate.format_sql","true");
		properties.put("hibernate.hbm2ddl.auto","update");//validate,drop,create,update
		properties.put("hibernate.jdbc.fetch_size", 200);
		properties.put("hibernate.jdbc.batch_size", 20);
		properties.put("hibernate.hbm2ddl.import_files", "initial_data.sql");
		properties.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "org.springframework.orm.hibernate5.SpringSessionContext");
		properties.put("hibernate.c3p0.acquire_increment",1);
		properties.put("hibernate.c3p0.idle_test_period",60);
		properties.put("hibernate.c3p0.min_size",0);
		properties.put("hibernate.c3p0.max_size",10);
		properties.put("hibernate.c3p0.max_statements",50);
		properties.put("hibernate.c3p0.timeout",10000);
		properties.put("hibernate.c3p0.acquireRetryAttempts",1);
		properties.put("hibernate.c3p0.acquireRetryDelay",250);
		factoryBean.setHibernateProperties(properties);
		return properties;
	}
	
	/** Create session For Every Object 
	 * @param sessionFactory
	 * @return
	 */
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}

}
