package generator;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import pollElements.MCTable;
import pollElements.Paragraph;
import pollElements.PollInput;

public class SceneParser {

	public MCTable[] getMCTables(Group page){
		
		ArrayList<MCTable> retList = new ArrayList<MCTable>();
		ObservableList<Node> lvlOneNodes= page.getChildren();
		for(Node lvlOne : lvlOneNodes) {
			if(lvlOne.getClass().equals(MCTable.class)) {
				MCTable table = (MCTable) lvlOne;
				retList.add(table);
			}
		}
		return retList.toArray(new MCTable[retList.size()]);
	}
	
	public PollInput[] getPollInputs(Group page) {
		
		ArrayList<PollInput> retList = new ArrayList<PollInput>();
		ObservableList<Node> lvlOneNodes = page.getChildren();
		for(Node lvlOne : lvlOneNodes) {
			if(lvlOne.getClass().equals(PollInput.class)) {
				PollInput input = (PollInput) lvlOne;
				retList.add(input);
			}
		}
		return retList.toArray(new PollInput[retList.size()]);
	}
	
	public Paragraph[] getParagraphs(Group page) {
		
		ArrayList<Paragraph> retList = new ArrayList<Paragraph>();
		ObservableList<Node> lvlOneNodes = page.getChildren();
		for(Node lvlOne : lvlOneNodes) {
			if(lvlOne.getClass().equals(Paragraph.class)) {
				Paragraph para = (Paragraph) lvlOne;
				retList.add(para);
			}
		}
		
		return retList.toArray(new Paragraph[retList.size()]);
	}
	
}
