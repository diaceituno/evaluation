package core;

import java.sql.SQLException;

import entities.Configuration;
import entities.User;

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
			
			DBControl dbControl = null;
			try {
				dbControl = DBControl.getInstance();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
	}
	
}
