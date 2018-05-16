package entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Poll {

	private SimpleIntegerProperty id;
	private SimpleStringProperty pollName;
	private String fxml;
	
	
	public Poll() {
		id = new SimpleIntegerProperty();
		pollName = new SimpleStringProperty();
	}
	
	public int getId() {
		return id.get();
	}
	public void setId(int id) {
		this.id.set(id);
	}
	public String getPollName() {
		return pollName.get();
	}
	public void setPollName(String pollName) {
		this.pollName.set(pollName);
	}
	public String getFxml() {
		return fxml;
	}
	public void setFxml(String fxml) {
		this.fxml = fxml;
	}

}
