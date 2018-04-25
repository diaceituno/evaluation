package core;

import java.sql.SQLException;

import entities.Configuration;

public class MainController {

	public static void main(String[] args) {

			Configuration config = Configuration.getInstance();
			config.setLdapDomain("miqr.local");
			config.setLdapRoot("dc=miqr,dc=local");
			config.setLDAPHost("miqr.dyndns.org");
			config.setLDAPPort("389");
			config.setLDAPUser("Subadmin");
			config.setLDAPPass("#qwandum40!");
			
			config.setMySQLHost("localhost");
			config.setMySQLPort("3306");
			config.setMySQLUser("diego");
			config.setMySQLPass("evaluation");
			config.setMySQLDatabase("eval");
	
			DBControl db = null;
			try {
				db = DBControl.getInstance();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(db != null) {
				
				try {
					db.getUserInputs(1,2,3);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
}
