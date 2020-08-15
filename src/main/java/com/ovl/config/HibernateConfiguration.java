package com.ovl.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.ovl.app.DBTYPE;
import com.ovl.details.ConnectionDetails;

public class HibernateConfiguration {

	public static SessionFactory SESSIONFACTORY;
	

	private static StandardServiceRegistry registry;
	private static ConnectionDetails connDetails;

	/*
	 * private static SessionFactory getSessionFactory() {
	 * 
	 * try { registry = new StandardServiceRegistryBuilder().configure().build();
	 * MetadataSources sources = new MetadataSources(registry); Metadata metadata =
	 * sources.getMetadataBuilder().build(); return
	 * metadata.getSessionFactoryBuilder().build(); } catch (Exception e) {
	 * e.printStackTrace(); shutdownHibernate();
	 * 
	 * } return null; }
	 */

	public HibernateConfiguration(ConnectionDetails connectionDetails) {
		this.connDetails = connectionDetails;
	}

	public SessionFactory getSessionFactory() {
		System.out.println(SESSIONFACTORY == null);
		
		if(this.connDetails==null) {
			System.out.println("Connection details is null...");
		}
		
		else if (SESSIONFACTORY == null) {
			try {
				System.out.println("Hibernate congfiguration Started");
				Configuration configuration = new Configuration();
				Properties settings = new Properties();
				System.out.println(this.connDetails.getDbType());
				String driver = this.connDetails.getDbType();
				String url = "jdbc:";
				String dialect = "";

				if (driver == DBTYPE.MSSQL) {
					url += "mysql://" + this.connDetails.getHost() + ":" + this.connDetails.getPort() + "/"
							+ this.connDetails.getDatabaseName();
					dialect = "org.hibernate.dialect.MySQLDialect";

				} else if (driver == DBTYPE.ORACLE) {
					url += "oracle:thin:@" + this.connDetails.getHost() + ":" + this.connDetails.getPort() + ":"
							+ this.connDetails.getDatabaseName();// jdbc:oracle:thin:@host:1521:databaseName
					dialect = "org.hibernate.dialect.OracleDialect";
				}
				/*
				 * else if(driver=="DB2")
				 * url=connDetails.getHost()+connDetails.getPort()+connDetails.getDatabaseName()
				 * ; else if(driver=="SYBASE")
				 * url=connDetails.getHost()+connDetails.getPort()+connDetails.getDatabaseName()
				 * ;
				 */

				System.out.println("Connection Initialization is Started ....");
				System.out.println("URL : "+url);
				System.out.println("DIALECT : "+dialect);
				settings.put(Environment.DRIVER, driver);
				settings.put(Environment.USER, this.connDetails.getUsername());
				settings.put(Environment.PASS, this.connDetails.getPassword());
				settings.put(Environment.URL, url);
				settings.put(Environment.DIALECT, dialect);
				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.HBM2DDL_AUTO, "create-drop");

				System.out.println("Connection Initialization is Done");

				configuration.setProperties(settings);

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();

				return configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
				shutdownHibernate();
			}
		}
		return null;
	}

	public static void shutdownHibernate() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
	
}
