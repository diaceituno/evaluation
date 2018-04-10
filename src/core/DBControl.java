package core;

import entities.Configuration;

public class DBControl {

	private String url;
	
	//Singleton Pattern
	private static DBControl dbControl = null;
	private DBControl() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		updateURL();
	}
	
	public static DBControl getInstance() throws ClassNotFoundException {
		if(dbControl == null) {
			dbControl = new DBControl();
		}
		return dbControl;
	}
	
	private void updateURL() {
		Configuration config = Configuration.getInstance();
		String host = config.getMySQLHost();
		String port = config.getMySQLPort();
		String database = config.getMySQLDatabase();
		url = "jdbc:mysql://"+ host + ":" + port +"/" + database;
		System.out.println(url);
	}

}
