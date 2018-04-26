package pollElements;

import java.util.ArrayList;

import entities.Answer;
import entities.Question;
import entities.Table;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class MCTable extends Group{

	//Elements
	private ArrayList<Line> vLines;
	private ArrayList<Line> hLines;
	private ArrayList<TextArea> titles;
	private ArrayList<TextArea> questions;
	private Rectangle resizeRec;
	private Rectangle boundsRec;
	private Rectangle movRec;
	
	//Dimensions
	double tableWidth;
	double tableHeight;
	int rows;
	int cols;
	double positionX;
	double positionY;
	
	public MCTable() {
		vLines = new ArrayList<Line>();
		hLines = new ArrayList<Line>();
		titles = new ArrayList<TextArea>();
		questions = new ArrayList<TextArea>();
		resizeRec = new Rectangle();
		resizeRec.setWidth(6);
		resizeRec.setHeight(6);
		resizeRec.setFill(Color.LAWNGREEN);
		resizeRec.setCursor(Cursor.SE_RESIZE);
		movRec = new Rectangle();
		movRec.setWidth(6);
		movRec.setHeight(6);
		movRec.setFill(Color.BLUE);
		movRec.setCursor(Cursor.MOVE);
		boundsRec = new Rectangle();
		boundsRec.setFill(Color.LIGHTGRAY);
		this.getChildren().addAll(resizeRec,movRec);
	}
	
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public void setCols(int cols) {
		this.cols = cols;
	}
	
	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}
	
	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}
	
	public void setTableWidth(double tableWidth) {
		this.tableWidth = tableWidth;
	}
	
	public void setTableHeight(double tableHeight) {
		this.tableHeight = tableHeight;
	}

	public void generateNodes() {
		
		//Generate the vertical lines 
		for(int i=0; i<=cols; i++) {
			Line vLine = new Line();
			vLines.add(vLine);
			this.getChildren().add(vLine);
		}
		
		//Generate horizontal lines
		for(int i=0; i<=rows; i++) {
			Line hLine = new Line();
			hLines.add(hLine);
			this.getChildren().add(hLine);
		}
		
		//titleareas
		for(int i=0; i<cols; i++) {
			TextArea titleArea = new TextArea();
			titleArea.setWrapText(true);
			titles.add(titleArea);
			this.getChildren().add(titleArea);
		}
		
		//questionAreas
		for(int i=1;i<rows;i++) {
			TextArea questionArea = new TextArea();
			questionArea.setWrapText(true);
			questions.add(questionArea);
			this.getChildren().add(questionArea);
		}
		
		//Titlearea
		titles.get(0).setPromptText("TITEL");
	}

	public void generateContextMenus() {
		
		for(int i=1; i<titles.size(); i++) {
			ContextMenu cMenu = new ContextMenu();
			ToggleGroup tGroup = new ToggleGroup();
			RadioMenuItem value1 = new RadioMenuItem("Note 1");
			RadioMenuItem value2 = new RadioMenuItem("Note 2");
			RadioMenuItem value3 = new RadioMenuItem("Note 3");
			RadioMenuItem value4 = new RadioMenuItem("Note 4");
			RadioMenuItem value5 = new RadioMenuItem("Note 5");
			MenuItem keine = new MenuItem("Note entfernen");
			value1.setToggleGroup(tGroup);
			value2.setToggleGroup(tGroup);
			value3.setToggleGroup(tGroup);
			value4.setToggleGroup(tGroup);
			value5.setToggleGroup(tGroup);
			value1.setId("1");
			value2.setId("2");
			value3.setId("3");
			value4.setId("4");
			value5.setId("5");
			
			keine.setOnAction(e->{
				if(tGroup.getSelectedToggle() != null) {
					tGroup.getSelectedToggle().setSelected(false);
				}
			});
			
			cMenu.getItems().addAll(value1,value2,value3,value4,value5,keine);
			titles.get(i).setContextMenu(cMenu);
		}
		
	}
		
	public void generateHandlers() {
		
		resizeRec.setOnMousePressed(e->{
			boundsRec.setLayoutX(positionX);
			boundsRec.setLayoutY(positionY);
			boundsRec.setHeight(tableHeight);
			boundsRec.setWidth(tableWidth);
			this.getChildren().clear();
			this.getChildren().addAll(boundsRec,resizeRec);
			
		});
		
		resizeRec.setOnMouseDragged(e->{
			boundsRec.setHeight( e.getSceneY() - positionY );
			boundsRec.setWidth(e.getSceneX() - positionX);
			resizeRec.setLayoutX(e.getSceneX());
			resizeRec.setLayoutY(e.getSceneY());
		});
		
		resizeRec.setOnMouseReleased(e->{
			
			tableWidth = e.getSceneX() - positionX;
			tableHeight = e.getSceneY() - positionY;
			reposition();
			this.getChildren().clear();
			this.getChildren().addAll(hLines);
			this.getChildren().addAll(vLines);
			this.getChildren().addAll(titles);
			this.getChildren().addAll(questions);
			this.getChildren().addAll(resizeRec,movRec);
		});
		
		movRec.setOnMousePressed(e->{
			boundsRec.setWidth(tableWidth);
			boundsRec.setHeight(tableHeight);
			boundsRec.setLayoutX(positionX);
			boundsRec.setLayoutY(positionY);
			this.getChildren().clear();
			this.getChildren().addAll(boundsRec,movRec);
		});
		
		movRec.setOnMouseDragged(e->{
			boundsRec.setLayoutX(e.getSceneX() - (tableWidth/2));
			boundsRec.setLayoutY(e.getSceneY() + 6);
			movRec.setLayoutX(e.getSceneX());
			movRec.setLayoutY(e.getSceneY());
		});
		
		movRec.setOnMouseReleased(e->{
			positionX = e.getSceneX() - (tableWidth/2);
			positionY = e.getSceneY() + 6;
			reposition();
			this.getChildren().clear();
			this.getChildren().addAll(hLines);
			this.getChildren().addAll(vLines);
			this.getChildren().addAll(titles);
			this.getChildren().addAll(questions);
			this.getChildren().addAll(resizeRec,movRec);
		});
	}
	
	public void reposition() {
		int i=0;
		for(Line vLine : vLines) {
			double lPositionX = positionX + i*(tableWidth/cols);
			vLine.setStartX(lPositionX);
			vLine.setEndX(lPositionX);
			vLine.setStartY(positionY);
			vLine.setEndY(positionY + tableHeight);
			i++;
		}
		i=0;
		for(Line hLine : hLines) {
			double lPositionY = positionY + i*(tableHeight/rows);
			hLine.setStartY(lPositionY);
			hLine.setEndY(lPositionY);
			hLine.setStartX(positionX);
			hLine.setEndX(positionX + tableWidth);
			i++;
		}
	
		double aWidth = tableWidth/cols;
		double aHeight = tableHeight/rows;
		i=0;
		for(TextArea titleArea : titles) {
			titleArea.setLayoutX(positionX + i*aWidth);
			titleArea.setLayoutY(positionY);
			titleArea.setPrefWidth(aWidth);
			titleArea.setPrefHeight(aHeight);
			i++;
		}
		i=1;
		for(TextArea questionArea : questions) {
			questionArea.setLayoutX(positionX);
			questionArea.setLayoutY(positionY + i*aHeight);
			questionArea.setPrefWidth(aWidth);
			questionArea.setPrefHeight(aHeight);
			i++;
		}
		
		resizeRec.setLayoutX(positionX + tableWidth);
		resizeRec.setLayoutY(positionY + tableHeight);
		
		movRec.setLayoutY(positionY - 6);
		movRec.setLayoutX(positionX + (tableWidth/2));
	}
	
	public void addRow() {
		Line hLine = new Line();
		hLines.add(hLine);
		TextArea area = new TextArea();
		questions.add(area);
		this.getChildren().addAll(hLine,area);
		rows++;
		reposition();
	}
	
	public void addCol() {
		
		ContextMenu cMenu = new ContextMenu();
		ToggleGroup tGroup = new ToggleGroup();
		RadioMenuItem value1 = new RadioMenuItem("Note 1");
		RadioMenuItem value2 = new RadioMenuItem("Note 2");
		RadioMenuItem value3 = new RadioMenuItem("Note 3");
		RadioMenuItem value4 = new RadioMenuItem("Note 4");
		RadioMenuItem value5 = new RadioMenuItem("Note 5");
		MenuItem keine = new MenuItem("Note entfernen");
		value1.setToggleGroup(tGroup);
		value2.setToggleGroup(tGroup);
		value3.setToggleGroup(tGroup);
		value4.setToggleGroup(tGroup);
		value5.setToggleGroup(tGroup);
		value1.setId("1");
		value2.setId("2");
		value3.setId("3");
		value4.setId("4");
		value5.setId("5");
		
		keine.setOnAction(e->{
			if(tGroup.getSelectedToggle() != null) {
				tGroup.getSelectedToggle().setSelected(false);
			}
		});
		cMenu.getItems().addAll(value1,value2,value3,value4,value5,keine);
		Line vLine = new Line();
		vLines.add(vLine);
		TextArea area = new TextArea();
		area.setContextMenu(cMenu);
		titles.add(area);
		this.getChildren().addAll(vLine,area);
		cols++;
		reposition();
	}

	public void removeRow() {
		this.getChildren().remove(questions.get(questions.size() -1 ));
		this.getChildren().remove(hLines.get(hLines.size() - 1));
		questions.remove(questions.size() - 1);
		hLines.remove(hLines.size() - 1);
		rows--;
		reposition();
	}

	public void removeCol() {
		this.getChildren().remove(titles.get(titles.size() -1 ));
		this.getChildren().remove(vLines.get(vLines.size() - 1));
		titles.remove(titles.size() - 1);
		vLines.remove(vLines.size() - 1);
		cols--;
		reposition();
	}

	public Table getTable() {
		
		Table table = new Table();
		table.setTitle(titles.get(0).getText());
		Answer answers[] = new Answer[titles.size() - 1];
		Question tableQuestions[] = new Question[questions.size()]; 
		
		for(int i=1; i<titles.size(); i++) {
			TextArea area = titles.get(i);
			String value = null;
			ContextMenu cMenu = area.getContextMenu();
			if(cMenu != null) {
				ToggleGroup tGroup = ((RadioMenuItem) cMenu.getItems().get(0)).getToggleGroup();
				RadioMenuItem item = (RadioMenuItem) tGroup.getSelectedToggle();
				if(item != null) {
					value = item.getId();
				}
			}
			answers[i-1] = new Answer();
			answers[i-1].setAnswer(area.getText());
			if(value != null) {
				answers[i-1].setValue(Integer.parseInt(value));
			}	
		}
		
		for(int i=0;i<questions.size();i++) {
			TextArea area = questions.get(i);
			tableQuestions[i] = new Question();
			tableQuestions[i].setQuestion(area.getText());
		}
		
		table.setAnswers(answers);
		table.setQuestions(tableQuestions);
		
		return table;
	}
}
