package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Answer;
import entities.Branch;
import entities.Configuration;
import entities.Group;
import entities.Input;
import entities.Poll;
import entities.Question;
import entities.Table;
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
	
	//TABLES
	private final String GET_TABLES = "select * from polltables where " + POLL_ID + "=";
	private final String TABLE_ID = "tableID";
	private final String TABLE_TITLE = "tableTitle";
	
	//Answers
	private final String GET_ANSWERS = "select * from tableanswers where " + TABLE_ID + "=";
	private final String ANSWER_ID  = "answerID";
	private final String ANSWER = "answer";
	private final String ANSWER_VALUE = "value";
	
	//QUESTIONS
	private final String GET_QUESTIONS = "select * from tablequestions where " + TABLE_ID + "=";
	private final String QUESTION_ID = "questionID";
	private final String QUESTION = "question";
	
	//INPUTS
	private final String GET_INPUTS = "select * from pollinputs where " + POLL_ID + "=";
	private final String INPUT_ID = "inputID";
	private final String INPUT_QUESTION = "inputQuestion";
	
	//GROUPPOLLS
	private final String GET_GROUP_POLLS = "select " + POLL_ID + " from pollsgroups where " + GROUP_ID + "="; 
	
	//Answers
	private final String GET_USER_ANSWERS1 = "select questionID, answerID from (select userID, groupID from users where "
											+ GROUP_ID + "=";
	private final String GET_USER_ANSWERS2 = ") as userView inner join useranswers on userView.userID = useranswers.userID";
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

	public Poll[] getPollsInBranch(int branchID) throws SQLException {
		
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
	
	public User[] getUsersInGroup(int groupID) throws SQLException {
		
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

	public Table[] getTablesInPoll(int pollID) throws SQLException {
		
		String query = GET_TABLES + pollID;
		Configuration config = Configuration.getInstance();
		Connection connection = DriverManager.getConnection(url, config.getMySQLUser(), config.getMySQLPass());
		Statement statement = connection.createStatement();
		ArrayList<Table> retList = new ArrayList<Table>();
		
		ResultSet resultSet = statement.executeQuery(query);
		while(resultSet.next()) {
			Table tmpTable = new Table();
			tmpTable.setId(resultSet.getInt(TABLE_ID));
			tmpTable.setPolliD(resultSet.getInt(POLL_ID));
			tmpTable.setTitle(resultSet.getString(TABLE_TITLE));
			retList.add(tmpTable);
		}
		connection.close();
		
		return retList.toArray(new Table[retList.size()]);
	}

	public Answer[] getTableAnswers(int tableID) throws SQLException {
		
		String query = GET_ANSWERS + tableID;
		Configuration config = Configuration.getInstance();
		Connection connection = DriverManager.getConnection(url, config.getMySQLUser(), config.getMySQLPass());
		Statement statement = connection.createStatement();
		ArrayList<Answer> retList = new ArrayList<Answer>();
		
		ResultSet resultSet = statement.executeQuery(query);
		while(resultSet.next()) {
			Answer tmpAnswer = new Answer();
			tmpAnswer.setId(resultSet.getInt(ANSWER_ID));
			tmpAnswer.setTableID(resultSet.getInt(TABLE_ID));
			tmpAnswer.setAnswer(resultSet.getString(ANSWER));
			tmpAnswer.setValue(resultSet.getInt(ANSWER_VALUE));
			retList.add(tmpAnswer);
		}
		connection.close();
		
		return retList.toArray(new Answer[retList.size()]);
	}

	public Question[] getTableQuestions(int tableID) throws SQLException {
		
		String query = GET_QUESTIONS + tableID;
		Configuration config = Configuration.getInstance();
		Connection connection = DriverManager.getConnection(url, config.getMySQLUser(), config.getMySQLPass());
		Statement statement = connection.createStatement();
		ArrayList<Question> retList = new ArrayList<Question>();
		
		ResultSet resultSet = statement.executeQuery(query);
		while(resultSet.next()) {
			Question tmpQuestion = new Question();
			tmpQuestion.setId(resultSet.getInt(QUESTION_ID));
			tmpQuestion.setTableID(resultSet.getInt(TABLE_ID));
			tmpQuestion.setQuestion(resultSet.getString(QUESTION));
			retList.add(tmpQuestion);
		}
		connection.close();
		
		return retList.toArray(new Question[retList.size()]);
	}

	public Input[] getPollInputs(int pollID) throws SQLException {
		
		String query = GET_INPUTS + pollID;
		Configuration config = Configuration.getInstance();
		Connection connection = DriverManager.getConnection(url, config.getMySQLUser(), config.getMySQLPass());
		Statement statement = connection.createStatement();
		ArrayList<Input> retList = new ArrayList<Input>();
		
		ResultSet resultSet = statement.executeQuery(query);
		while(resultSet.next()) {
			Input tmpInput = new Input();
			tmpInput.setId(resultSet.getInt(INPUT_ID));
			tmpInput.setPollID(resultSet.getInt(POLL_ID));
			tmpInput.setQuestion(resultSet.getString(INPUT_QUESTION));
			retList.add(tmpInput);
		}
		connection.close();
		
		return retList.toArray(new Input[retList.size()]);
	}

	public Integer[] getGroupPollIDs(int groupID) throws SQLException {
		
		String query = GET_GROUP_POLLS + groupID;
		Configuration config = Configuration.getInstance();
		Connection connection = DriverManager.getConnection(url, config.getMySQLUser(), config.getMySQLPass());
		Statement statement = connection.createStatement();
		ArrayList<Integer> retList = new ArrayList<Integer>();
		
		ResultSet resultSet = statement.executeQuery(query);
		while(resultSet.next()) {
			retList.add(resultSet.getInt(POLL_ID));
		}
		connection.close();
		
		return retList.toArray(new Integer[retList.size()]);
	}

	public ResultSet getUserAnswers(int ... groupIDs) throws SQLException{
		
		String query = GET_USER_ANSWERS1;
		for(int i=0;i<groupIDs.length-1;i++) {
			query += groupIDs[i] + " or " + GROUP_ID + "="; 
		}
		query+= groupIDs[groupIDs.length - 1] + GET_USER_ANSWERS2;
		Configuration config = Configuration.getInstance();
		Connection connection = DriverManager.getConnection(url, config.getMySQLUser(), config.getMySQLPass());
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery(query);
		return resultSet;
	}
}
