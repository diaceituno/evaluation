package core;

import com.unboundid.ldap.sdk.Attribute;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.LDAPSearchException;
import com.unboundid.ldap.sdk.SearchResultEntry;

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
			config.setMySQLDatabase("eval");
			
			LDAPControl ldap = new LDAPControl();
			try {
				ldap.connect();
			} catch (LDAPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ldap.login();
			} catch (LDAPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				SearchResultEntry[] entries = ldap.searchOU(Configuration.getInstance().getLdapRoot());
				for(SearchResultEntry entry : entries) {
					
					for(Attribute attribute: entry.getAttributes()) {
						System.out.println(attribute.getName() + ": " + attribute.getValue());
					}
				}
			} catch (LDAPSearchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ldap.disconnect();
	}
}
