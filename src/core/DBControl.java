package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Branch;
import entities.Configuration;
import entities.Group;
import entities.Poll;
import entities.User;

public class DBControl {
	
	private String url;
	
	//BRANCHES
	private final String GET_BRANCHES = "select * from branches";
	private final String BRANCH_ID = "branchID";
	private final String BRANCH_NAME = "branchName";
	
	//GROUPS
	private final String GET_GROUPS = "select * from `groups` where " + BRANCH_ID + "=";
	private final String GROUP_ID = "groupID";
	private final String GROUP_NAME = "groupName";
	
	//POLLS
	private final String GET_POLLS = "select * from polls where " + BRANCH_ID + "=";
	private final String POLL_ID = "pollID";
	private final String POLL_NAME = "pollName";
	private final String POLL_FXML = "fxml";
	
	//USERS
	private final String GET_USERS = "select * from users where " + GROUP_ID + "=";
	private final String USER_ID = "userID";
	private final String USER_NAME = "userName";
	
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
	
	
	//updating configuration values
	private void updateURL() {
		Configuration config = Configuration.getInstance();
		String host = config.getMySQLHost();
		String port = config.getMySQLPort();
		String database = config.getMySQLDatabase();
		url = "jdbc:mysql://"+ host + ":" + port +"/" + database;
		System.out.println(url);
	}

	//GET METHODS
	public Branch[] getBranches() throws SQLException {
		
		Configuration config = Configuration.getInstance();
		Connection connection = DriverManager.getConnection(url, config.getMySQLUser(),config.getMySQLPass());
		Statement statement = connection.createStatement();
		ArrayList<Branch> retList = new ArrayList<Branch>();
		
		ResultSet resultSet = statement.executeQuery(GET_BRANCHES);
		while(resultSet.next()) {
			Branch tmpBranch = new Branch();
			tmpBranch.setId(resultSet.getInt(BRANCH_ID));
			tmpBranch.setName(resultSet.getString(BRANCH_NAME));
			retList.add(tmpBranch);
		}
		connection.close();
		
		return retList.toArray(new Branch[retList.size()]);
	}
	
	public Group[] getGroupsInBranch(int branchID) throws SQLException {
		
		String query = GET_GROUPS + branchID + ";";
		Configuration config = Configuration.getInstance();
		Connection connection = DriverManager.getConnection(url, config.getMySQLUser(), config.getMySQLPass());
		Statement statement = connection.createStatement();
		ArrayList<Group> retList = new ArrayList<Group>();
		
		ResultSet resultSet = statement.executeQuery(query);
		while(resultSet.next()) {
			Group tmpGroup = new Group();
			tmpGroup.setId(resultSet.getInt(GROUP_ID));
			tmpGroup.setBranchID(resultSet.getInt(BRANCH_ID));
			tmpGroup.setGroupName(resultSet.getString(GROUP_NAME));
			retList.add(tmpGroup);
		}
		connection.close();
		
		return retList.toArray(new Group[retList.size()]);
	}

	public Poll[] getPolls(int branchID) throws SQLException {
		
		String query = GET_POLLS + branchID;
		Configuration config = Configuration.getInstance();
		Connection connection = DriverManager.getConnection(url, config.getMySQLUser(), config.getMySQLPass());
		Statement statement = connection.createStatement();
		ArrayList<Poll> retList = new ArrayList<Poll>();
		
		ResultSet resultSet = statement.executeQuery(query);
		while(resultSet.next()) {
			Poll tmpPoll = new Poll();
			tmpPoll.setId(resultSet.getInt(POLL_ID));
			tmpPoll.setPollName(resultSet.getString(POLL_NAME));
			tmpPoll.setFxml(resultSet.getString(POLL_FXML));
			retList.add(tmpPoll);
		}
		connection.close();
		
		return retList.toArray(new Poll[retList.size()]);
	}
	
	public User[] getUsers(int groupID) throws SQLException {
		
		String query = GET_USERS + groupID;
		Configuration config = Configuration.getInstance();
		Connection connection = DriverManager.getConnection(url, config.getMySQLUser(), config.getMySQLPass());
		Statement statement = connection.createStatement();
		ArrayList<User> retList = new ArrayList<User>();
		
		ResultSet resultSet = statement.executeQuery(query);
		while(resultSet.next()) {
			User tmpUser = new User();
			tmpUser.setId(resultSet.getInt(USER_ID));
			tmpUser.setGroupID(resultSet.getInt(GROUP_ID));
			tmpUser.setUserName(resultSet.getString(USER_NAME));
			retList.add(tmpUser);
		}
		connection.close();
		
		return retList.toArray(new User[retList.size()]);
	}
}
