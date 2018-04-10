package core;

import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.LDAPSearchException;

import entities.Configuration;

public class MainController {

	public static void main(String[] args) {

			Configuration config = Configuration.getInstance();
			config.setLdapDomain("miqr.local");
			config.setLdapRoot("dc=miqr,dc=local");
			config.setLDAPHost("miqr.dyndns.org");
			config.setLDAPPort("389");
			config.setLDAPUser("Susbadmin");
			config.setLDAPPass("#qwandum40!");
			
			config.setMySQLHost("localhost");
			config.setMySQLPort("3306");
			config.setMySQLDatabase("eval");
			
			try {
				DBControl dbControl = DBControl.getInstance();
			} catch (ClassNotFoundException e) {
				Logger.getInstance().logError(e);
			}
	}
}
