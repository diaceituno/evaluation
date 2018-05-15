package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import entities.Answer;
import entities.Branch;
import entities.Group;
import entities.Input;
import entities.Poll;
import entities.Question;
import entities.Table;
import entities.User;

public class EntityManager {

	//Singleton Patter
	private static EntityManager entityManager = null;
	
	private EntityManager() {}
	
	public static EntityManager getInstance() {
		if(entityManager == null) {
			entityManager = new EntityManager();
		}
		return entityManager;
	}
	
	
	//Entities and mappings
	private Branch branch;
	private Poll poll;
	private Group group;
	
	private Branch[] branches;
	
	private Poll[] polls;
	
	private Group[] groups;
	
	private HashMap<Group,Poll[]> groupPolls;
	
	private Table[] tables;
	
	private HashMap<Table,Question[]> tableQuestions;
	
	private HashMap<Table,Answer[]> tableAnswers;
	
	private Input[] inputs;
	
	private User[] users;
	
	//Loading Methods
	
	private ResultSet userAnswers;
	
	public boolean loadBranches() {
		
		DBControl dbControl = null;
		Logger logger = Logger.getInstance();
		try {
			dbControl = DBControl.getInstance();
		} catch (ClassNotFoundException e) {
			logger.logError(e);
		}
		
		if(dbControl != null) {
			try {
				branches = dbControl.getBranches();
				return true;
			} catch (SQLException e) {
				logger.logError(e);
			}
		}
		
		return false;
	}

	public boolean loadPolls() {
		
		DBControl dbControl = null;
		Logger logger = Logger.getInstance();
		try {
			dbControl = DBControl.getInstance();
		} catch (ClassNotFoundException e) {
			logger.logError(e);
		}
		
		if(dbControl != null) {
			try {
				polls = dbControl.getPollsInBranch(branch.getId());
				return true;
			} catch (SQLException e) {
				logger.logError(e);
			}
		}
		
		return false;
	}

	public boolean loadGroups() {
		
		DBControl dbControl = null;
		Logger logger = Logger.getInstance();
		try {
			dbControl = DBControl.getInstance();
		} catch (ClassNotFoundException e) {
			logger.logError(e);
		}
		
		if(dbControl != null) {
			try {
				groups = dbControl.getGroupsInBranch(branch.getId());
				return true;
			} catch (SQLException e) {
				logger.logError(e);
			}
		}
		return false;
	}
	
	public boolean loadGroupPolls() {
		
		groupPolls = new HashMap<Group,Poll[]>();
		DBControl dbControl = null;
		boolean sorted = true;
		Logger logger = Logger.getInstance();
		try {
			dbControl = DBControl.getInstance();
		} catch (ClassNotFoundException e) {
			logger.logError(e);
		}
		
		if(dbControl != null) {
			for(Group group : groups) {
				Integer pollIDs[] = null;
				try {
					pollIDs = dbControl.getGroupPollIDs(group.getId());
				} catch (SQLException e) {
					e.printStackTrace();
					sorted = false;
					logger.logError(e);
				}
				
				if(pollIDs != null) {
					ArrayList<Poll> pollList = new ArrayList<Poll>();
					for(Integer pollID : pollIDs) {
						for(Poll poll : polls) {
							if(pollID.intValue() == poll.getId()) {
								pollList.add(poll);
							}
						}
					}
					groupPolls.put(group, pollList.toArray(new Poll[pollList.size()]));
				}
			}
			return sorted;
		}
		return false;
	}

	public boolean loadTables() {
		
		DBControl dbControl = null;
		Logger logger = Logger.getInstance();
		try {
			dbControl = DBControl.getInstance();
		} catch (ClassNotFoundException e) {
			logger.logError(e);
		}
		
		if(dbControl != null) {
			try {
				tables = dbControl.getTablesInPoll(poll.getId());
				return true;
			} catch (SQLException e) {
				logger.logError(e);
			}
		}
		return false;
	}

	public boolean loadQuestions() {
		tableQuestions = new HashMap<Table,Question[]>();
		DBControl dbControl = null;
		Logger logger = Logger.getInstance();
		boolean loaded = true;
		
		try {
			dbControl = DBControl.getInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(dbControl != null) {
			for(Table table : tables) {
				try {
					Question[] questions = dbControl.getTableQuestions(table.getId());
					tableQuestions.put(table, questions);
				} catch (SQLException e) {
					loaded = false;
					logger.logError(e);
				}
			}
			return loaded;
		}
		return false;
	}

	public boolean loadAnswers() {
		tableAnswers = new HashMap<Table,Answer[]>();
		
		DBControl dbControl = null;
		Logger logger = Logger.getInstance();
		boolean loaded = true;
		try {
			dbControl = DBControl.getInstance();
		} catch (ClassNotFoundException e) {
			logger.logError(e);
		}
		
		if(dbControl != null) {
			for(Table table : tables) {
				try {
					Answer[] answers = dbControl.getTableAnswers(table.getId());
					tableAnswers.put(table, answers);
				} catch (SQLException e) {
					loaded = false;
					logger.logError(e);
				}
			}
			return loaded;
		}
		return false;
	}

	public boolean loadInputs() {
		
		DBControl dbControl = null;
		Logger logger = Logger.getInstance();
		try {
			dbControl = DBControl.getInstance();
		} catch (ClassNotFoundException e) {
			logger.logError(e);
		}
		
		if(dbControl != null) {
			try {
				inputs = dbControl.getPollInputs(poll.getId());
			} catch (SQLException e) {
				logger.logError(e);
				return false;
			}
			return true;
		}
		return false;
	}
	
	public boolean loadUsers() {
		
		DBControl dbControl = null;
		Logger logger = Logger.getInstance();
		try {
			dbControl = DBControl.getInstance();
		} catch (ClassNotFoundException e) {
			logger.logError(e);
		}
		
		if(dbControl != null) {
			try {
				users = dbControl.getUsersInGroup(group.getId());
			} catch (SQLException e) {
				logger.logError(e);
				return false;
			}
			return true;
		}
		return false;
	}
	
	public boolean loadUserAnswers(Group...groups) {
		
		DBControl dbControl = null;
		Logger logger = Logger.getInstance();
		try {
			dbControl = DBControl.getInstance();
		} catch (ClassNotFoundException e) {
			logger.logError(e);
		}
		
		if(dbControl != null) {
			int iDs[] = new int[groups.length];
			for(int i=0; i<groups.length; i++) {
				iDs[i] = groups[i].getId();
			}
			try {
				userAnswers = dbControl.getUserAnswers(iDs);
			} catch (SQLException e) {
				logger.logError(e);
				return false;
			}
			return true;
		}
		
		return false;
	}
	
	//set Methods
	public void setBranch(Branch branch) {
		this.branch = branch;
		loadPolls();
	}
	
	public void setPoll(Poll poll) {
		this.poll = poll;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
	//get Methods
	public Branch getBranch() {
		return branch;
	}
	
 	public Branch[] getBranches() {
		return branches;
	}
	
	public Poll[] getPolls() {
		return polls;
	}

	public Group[] getGroups() {
		return groups;
	}
	
	public Poll[] getPolls(Group group) {
		return groupPolls.get(group);
	}
	
	public User[] getUsers() {
		return users;
	}

	public Table[] getTables() {
		return tables;
	}
	
	public Input[] getInputs() {
		return inputs;
	}

	public Question[] getQuestions(Table table) {
		return tableQuestions.get(table);
	}
	
	public Answer[] getAnswers(Table table) {
		return tableAnswers.get(table);
	}

	public ResultSet getUserAnswers() {
		return userAnswers;
	}
}
