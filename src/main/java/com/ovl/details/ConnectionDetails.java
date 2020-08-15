package com.ovl.details;

public class ConnectionDetails {

	private String username;
	private String password;
	private String host;
	private String port;
	private String databaseName;
	private String dbType;
	
	public ConnectionDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConnectionDetails(String username2, String password2, String host, String port2, String database,
			String db_type) {
		this.username=username2;
		this.databaseName=database;
		this.dbType=db_type;
		this.host=host;
		this.port=port2;
		this.password=password2;
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	@Override
	public String toString() {
		return "ConnectionDetails [username=" + username + ", password=" + password + ", host=" + host + ", port="
				+ port + ", databaseName=" + databaseName + ", dbType=" + dbType + "]";
	}
	
	
	
}
