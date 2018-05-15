package core;

import entities.Branch;
import entities.Configuration;
import javafx.application.Application;
import javafx.stage.Stage;
import viewControl.EditorControl;


public class MainController extends Application{

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
			launch(args);
			
	}

	@Override
	public void start(Stage stage) throws Exception {
		EntityManager em = EntityManager.getInstance();
		em.loadBranches();
		em.setBranch(em.getBranches()[0]);
		EditorControl edit = new EditorControl();
		edit.generateHandlers();
		edit.show();
	}	
}
