package generator;

import entities.Configuration;

public class ConfigParser {

	//CONSTANTS
	private static final String LDAPHOST = "ldapHost";
	private static final String LDAPROOT = "ldapRoot";
	private static final String LDAPDOMAIN = "ldapDomain";
	private static final String LDAPPORT = "ldapPort";
	private static final String MYSQLHOST = "mysqlHost";
	private static final String MYSQLPORT = "mysqlPort";
	private static final String MYSQLDATABASE = "mysqlDatabase";
	private static final String MYSQLUSER = "mysqlUser";
	private static final String MYSQLPASS = "mysqlPass";
	
	public String genConfigString() {
		
		Configuration config = Configuration.getInstance();
		String configString = LDAPHOST + "=" + config.getLDAPHost() + ";" +
							  LDAPDOMAIN + "=" + config.getLdapDomain() + ";" +
							  LDAPROOT + "=" + config.getLdapRoot() + ";" +
							  LDAPPORT + "=" + config.getLDAPPort() + ";" +
							  MYSQLHOST + "=" + config.getMySQLHost() + ";" + 
							  MYSQLPORT + "=" + config.getMySQLPort() + ";" + 
							  MYSQLDATABASE + "=" + config.getMySQLDatabase() + ";" +
							  MYSQLUSER + "=" + config.getMySQLUser() + ";" +
							  MYSQLPASS + "=" + config.getMySQLPass();

		return configString;
	}
	
	public void genConfiguration(String configString) {
		
		Configuration config = Configuration.getInstance();
		String[] keyValuePairs = configString.split(";");
		for(String keyValueString : keyValuePairs) {
			String[] keyValueArray = keyValueString.split("=");
			String key = keyValueArray[0];
			String value = keyValueArray[1];
			
			if(!value.equals("null")) {
				switch(key) {
				case LDAPHOST:
					config.setLDAPHost(value);
					break;
				case LDAPROOT:
					config.setLdapRoot(value);
				case LDAPDOMAIN:
					config.setLdapDomain(value);
					break;
				case LDAPPORT:
					config.setLDAPPort(value);
					break;
				case MYSQLHOST:
					config.setMySQLHost(value);
					break;
				case MYSQLPORT:
					config.setMySQLPort(value);
					break;
				case MYSQLDATABASE:
					config.setMySQLDatabase(value);
					break;
				case MYSQLUSER:
					config.setMySQLUser(value);
					break;
				case MYSQLPASS:
					config.setMySQLPass(value);
					break;
				}
			}
		}
	}
}
