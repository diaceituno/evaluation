package core;

import java.util.List;

import com.unboundid.ldap.sdk.Filter;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPConnectionOptions;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.LDAPSearchException;
import com.unboundid.ldap.sdk.SearchRequest;
import com.unboundid.ldap.sdk.SearchResult;
import com.unboundid.ldap.sdk.SearchResultEntry;
import com.unboundid.ldap.sdk.SearchScope;
import com.unboundid.ldap.sdk.SimpleBindRequest;

import entities.Configuration;

public class LDAPControl {

	private Configuration config;
	private LDAPConnection con;
	
	private final static String OU = "organizationalUnit";
	private final static String USER = "user";
	private final static String OCLASS = "objectClass";
	private final static String OCATH = "objectCategory";
	
	public LDAPControl() {
		
		config = Configuration.getInstance();
		LDAPConnectionOptions opts = new LDAPConnectionOptions();
		opts.setConnectTimeoutMillis(5000);
		con = new LDAPConnection();
		con.setConnectionOptions(opts);
	}
	
	//Connection 
	public void connect() throws LDAPException {
		
		String host = config.getLDAPHost();
		int port = Integer.parseInt(config.getLDAPPort());
		con.connect(host, port);
	}
	public void disconnect() {
		con.close();
	}
	
	//Binding
	public void login() throws LDAPException {
		
		String domain = config.getLdapDomain();
		String user = config.getLDAPUser() + "@" + domain;
		String pw = config.getLDAPPass();
		SimpleBindRequest bindRequest = new SimpleBindRequest(user,pw);
		con.bind(bindRequest);
	}
	
	public SearchResultEntry[] searchOU(String path) throws LDAPSearchException {
		
		Filter filter = Filter.createEqualityFilter(OCATH, OU);
		SearchRequest searchRequest = new SearchRequest(path,SearchScope.ONE, filter);
		SearchResult searchResult = con.search(searchRequest);
		searchResult = con.search(searchRequest);
		
		List<SearchResultEntry> entryList = searchResult.getSearchEntries();
		SearchResultEntry[] entryArray = entryList.toArray(new SearchResultEntry[entryList.size()]);
		return entryArray;
	}

	public SearchResultEntry[] searchUser(String path) throws LDAPSearchException {
		
		Filter filter = Filter.createEqualityFilter(OCLASS, USER);
		SearchRequest searchRequest = new SearchRequest(path,SearchScope.ONE, filter);
		SearchResult searchResult = con.search(searchRequest);
		searchResult = con.search(searchRequest);
		
		List<SearchResultEntry> entryList = searchResult.getSearchEntries();
		SearchResultEntry[] entryArray = entryList.toArray(new SearchResultEntry[entryList.size()]);
		return entryArray;
	}
}
