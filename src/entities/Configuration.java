package entities;

public class Configuration {

	//MySQL
	private String mySQLHost;
	private String mySQLPort;
	private String mySQLUser;
	private String mySQLPass;
	private String mySQLDatabase;
	
	//LDAP
	private String ldapDomain;
	private String ldapPHost;
	private String ldapPort;
	private String ldapUser;
	private String ldapPass;
	private String ldapRoot;

	//Singleton pattern
	private static Configuration config = null;
	private Configuration() {}
	
	public static Configuration getInstance() {
		if(config == null) {
			config = new Configuration();
		}
		return config;
	}
	
	//SETTERS
	public void setMySQLHost(String mySQLHost) {
		this.mySQLHost = mySQLHost;
	}
	public void setMySQLDatabase(String mySQLDatabase) {
		this.mySQLDatabase = mySQLDatabase;
	}
	public void setMySQLPort(String mySQLPort) {
		this.mySQLPort = mySQLPort;
	}
	public void setMySQLUser(String mySQLUser) {
		this.mySQLUser = mySQLUser;
	}
	public void setMySQLPass(String mySQLPass) {
		this.mySQLPass = mySQLPass;
	}
	public void setLdapDomain(String ldapDomain) {
		this.ldapDomain = ldapDomain;
	}
	public void setLDAPHost(String lDAPHost) {
		ldapPHost = lDAPHost;
	}
	public void setLdapRoot(String ldapRoot) {
		this.ldapRoot = ldapRoot;
	}
	public void setLDAPPort(String lDAPPort) {
		ldapPort = lDAPPort;
	}
	public void setLDAPUser(String lDAPUser) {
		ldapUser = lDAPUser;
	}
	public void setLDAPPass(String lDAPPass) {
		ldapPass = lDAPPass;
	}
	
	//GETTERS
	public String getMySQLHost() {
		return mySQLHost;
	}
	public String getMySQLDatabase() {
		return mySQLDatabase;
	}
	public String getMySQLPort() {
		return mySQLPort;
	}
	public String getMySQLUser() {
		return mySQLUser;
	}
	public String getMySQLPass() {
		return mySQLPass;
	}
	public String getLdapDomain() {
		return ldapDomain;
	}
	public String getLDAPHost() {
		return ldapPHost;
	}
	public String getLdapRoot() {
		return ldapRoot;
	}
	public String getLDAPPort() {
		return ldapPort;
	}
	public String getLDAPUser() {
		return ldapUser;
	}
	public String getLDAPPass() {
		return ldapPass;
	}
}
