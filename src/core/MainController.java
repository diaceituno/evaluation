package core;

import java.sql.SQLException;

import entities.Configuration;
import entities.Group;
import entities.Poll;
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
			try {
				DBControl.getInstance().getUserAnswers(1,2,3);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			EntityManager em = EntityManager.getInstance();
			System.out.println(em.loadBranches());
			em.setBranch(em.getBranches()[1]);
			System.out.println(em.loadPolls());
			em.setPoll(em.getPolls()[0]);
			System.out.println(em.loadGroups());
			em.setGroup(em.getGroups()[0]);
			System.out.println(em.loadGroupPolls());
			System.out.println(em.loadTables());
			System.out.println(em.loadQuestions());
			System.out.println(em.loadAnswers());
			System.out.println(em.loadInputs());
			System.out.println(em.loadUsers());
			for(User user :  em.getUsers()) {
				System.out.println(user.getId()+"|"+user.getUserName() + "|" + user.getGroupID());
			}
	}
	
}
