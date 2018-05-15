package viewControl;


import java.sql.SQLException;
import java.util.ArrayList;

import core.DBControl;
import core.EntityManager;
import entities.Branch;
import entities.Configuration;
import entities.Poll;
import entities.Table;
import generator.SceneParser;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pollElements.MCTable;
import pollElements.Paragraph;
import pollElements.PollInput;

public class EditorControl {

	private Stage stage;
	private Scene scene;
	private ScrollPane scrollPane;
	private AnchorPane page;
	private AnchorPane background;
	private MenuBar menuBar;
	private AnchorPane rootPane;
	private Group scrollGroup;
	private ArrayList<Group> pages;
	private int currentPage;
	private Label pageLabel;
	private Button nextBtn;
	private Button prevBtn;
	
	//DIMENSIONS
	private final double PAGE_HEIGHT = 800;
	private final double PAGE_WIDTH = 700;
	
	//TOOLS AND CONTROL
	private final int TABELLE = 1;
	private final int EINGABE = 2;
	private final int TEXT = 3;
	private boolean toolActive;
	private int tool;
	
	public EditorControl() {
		
		//initializing
		rootPane = new AnchorPane();
		menuBar = new MenuBar();
		scrollGroup = new Group();
		background = new AnchorPane();
		page = new AnchorPane();		
		scrollPane = new ScrollPane();
		stage = new Stage();
		scene = new Scene(rootPane);
		toolActive = false;
		pages = new ArrayList<Group>();
		currentPage = 0;
		pageLabel = new Label();
		nextBtn = new Button(">");
		prevBtn = new Button("<");
		nextBtn.setOnAction(e->{
			nextPage();
		});
		prevBtn.setOnAction(e->{
			prevPage();
		});
		
		
		//Dimensioning
		page.setPrefHeight(PAGE_HEIGHT);
		page.setPrefWidth(PAGE_WIDTH);
		background.setPrefHeight(PAGE_HEIGHT + 100);
		prevBtn.setMinHeight(24);
		prevBtn.setPrefHeight(24);
		nextBtn.setMinHeight(24);
		nextBtn.setPrefHeight(24);
		
		menuBar.prefWidthProperty().bind(stage.widthProperty());
		background.prefWidthProperty().bind(stage.widthProperty());
		scrollPane.prefHeightProperty().bind(stage.heightProperty());
		scrollPane.prefWidthProperty().bind(stage.widthProperty());
		
		background.prefWidthProperty().addListener((ob,ov,nv)->{
			page.setLayoutX((background.getPrefWidth() - page.getPrefWidth())/2);
			pageLabel.setLayoutX(page.getLayoutX() + page.getPrefWidth()/2);
			nextBtn.setLayoutX(page.getLayoutX() + 2*page.getPrefWidth()/3);
			prevBtn.setLayoutX(page.getLayoutX() + page.getPrefWidth()/3);
		});
		
		stage.setMaximized(true);
		
		//Positioning
		scrollPane.setLayoutX(0);
		scrollPane.setLayoutY(0);
		page.setLayoutY(50);
		pageLabel.setLayoutY(5);
		
		//Styling
		page.setStyle("-fx-background-color: white");
		background.setStyle("-fx-background-color: grey");
		page.setEffect(new DropShadow());
		
		//Adding
		newPage();
		page.getChildren().add(pages.get(0));
		updateLabel();
		scrollGroup.getChildren().addAll(background,page);
		background.getChildren().add(page);
		scrollPane.setContent(scrollGroup);
		rootPane.getChildren().addAll(scrollPane,menuBar,nextBtn,prevBtn,pageLabel);
		stage.setScene(scene);
		genMenuBar();
	}

	public void genMenuBar() {
		
		//GENERATING
		Menu datei = new Menu("Datei");
		Menu insert = new Menu("Einfügen");
		Menu seite = new Menu("Seite");
		
		MenuItem save = new MenuItem("Speichern");
		MenuItem load = new MenuItem("Laden");
		
		MenuItem newPage = new MenuItem("Neue Seite");
		MenuItem delPage = new MenuItem("Seite Löschen");
		MenuItem dupPage = new MenuItem("Seite Duplizieren");
		
		MenuItem table = new MenuItem("Tabelle");
		MenuItem input = new MenuItem("Eingabe");
		MenuItem text = new MenuItem("Text");
		
		//ADDING HANDLES
		save.setOnAction(e->{
			save();
		});
		
		load.setOnAction(e->{
			load();
		});
		
		newPage.setOnAction(e->{
			newPage();
		});
		
		delPage.setOnAction(e->{
			delPage();
		});
		
		dupPage.setOnAction(e->{
			dupPage();
		});
		
		table.setOnAction(e->{
			toolActive = true;
			tool = TABELLE;
			page.setCursor(Cursor.HAND);
		});
		
		input.setOnAction(e->{
			toolActive = true;
			tool = EINGABE;
			page.setCursor(Cursor.HAND);
		});
		
		text.setOnAction(e->{
			toolActive = true;
			tool = TEXT;
			page.setCursor(Cursor.HAND);
		});
		
		datei.getItems().addAll(save,load);
		seite.getItems().addAll(newPage,delPage,dupPage);
		insert.getItems().addAll(table,input,text);
		
		menuBar.getMenus().addAll(datei,seite,insert);
	}
	
	public void generateHandlers() {
		
		page.setOnMouseClicked(e->{
			if(toolActive) {
				switch(tool) {
				case TABELLE:
					MCTable table = new MCTable();
					table.setParent(page);
					table.setTableWidth(400);
					table.setTableHeight(400);
					table.setPositionX(e.getSceneX());
					table.setPositionY(e.getSceneY());
					table.setRows(3);
					table.setCols(3);
					table.generateNodes();
					pages.get(currentPage).getChildren().add(table);
					toolActive = false;
					page.setCursor(Cursor.DEFAULT);
					break;
				case EINGABE:
					PollInput input = new PollInput();
					input.setParent(page);
					input.setQuestionX(e.getSceneX());
					input.setQuestionY(e.getSceneY());
					input.generateNodes();
					pages.get(currentPage).getChildren().add(input);
					toolActive = false;
					page.setCursor(Cursor.DEFAULT);
					break;
				case TEXT:
					Paragraph par = new Paragraph();
					par.setParent(page);
					par.setX(e.getSceneX());					
					par.setY(e.getSceneY());
					pages.get(currentPage).getChildren().add(par);
					toolActive = false;
					page.setCursor(Cursor.DEFAULT);
					break;
				}
			}
		});
		
		scene.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ESCAPE)) {
				toolActive = false;
				page.setCursor(Cursor.DEFAULT);
			}
		});
		
	}
	
	public void show() {
		stage.showAndWait();
	}
	
	private void save() {
		
		//INPUT NAME
		Poll poll = new Poll();
		poll.setPollName("testpoll");
		
		Branch branch = EntityManager.getInstance().getBranch();
		
		SceneParser parser = new SceneParser();
		Configuration config = Configuration.getInstance();
		DBControl db = null;
		
		try {
			db  = DBControl.getInstance();
		} catch (ClassNotFoundException e) {
			//error message and log
		}
		if(db != null) {
			int pollID = 0;
			try {
				pollID = db.savePoll(branch, poll);
				System.out.println("pollID: "+pollID);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			poll.setId(pollID);
			for(Group group : pages) {
				MCTable tables[] = parser.getMCTables(group);
				PollInput inputs[] = parser.getPollInputs(group);
				for(MCTable table : tables) {
					try {
						Table toSave = table.getTable();
						int tID = db.saveTable(poll, toSave);
						toSave.setId(tID);
						db.saveQuestions(toSave);
						db.saveAnswers(toSave);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	private void load() {
		
	}
	
	private void newPage() {
		pages.add(new Group());
		updateLabel();
	}
	
	private void delPage() {
		page.getChildren().clear();
		pages.remove(currentPage);
		if(pages.size() > 0) {
			if(currentPage != 0) {
				prevPage();
			}else {
				page.getChildren().add(pages.get(currentPage));
			}
		}else {
			newPage();
			currentPage = -1;
			nextPage();
		}
		updateLabel();
	}
	
	private void dupPage() {
		newPage();   
		
		  
		int newPage = pages.size() - 1;
		SceneParser parser = new SceneParser();
		MCTable tables[] = parser.getMCTables(pages.get(currentPage));
		PollInput inputs[] = parser.getPollInputs(pages.get(currentPage));
		Paragraph pars[] = parser.getParagraphs(pages.get(currentPage));
		MCTable ntables[] = new MCTable[tables.length];
		PollInput ninputs[] = new PollInput[inputs.length];
		Paragraph npars[] = new Paragraph[pars.length];
		for(int i = 0; i < tables.length; i++) {
			ntables[i] = tables[i].duplicate();
			pages.get(newPage).getChildren().add(ntables[i]);
		}
		for(int i = 0; i < inputs.length; i++) {
			ninputs[i] = inputs[i].duplicate();
			pages.get(newPage).getChildren().add(ninputs[i]);
		}
		for(int i=0; i < pars.length; i++) {
			npars[i] = pars[i].duplicate();
			pages.get(newPage).getChildren().add(npars[i]);
		}
		page.getChildren().clear();
		page.getChildren().add(pages.get(newPage));
		updateLabel();
	}

	private void updateLabel() {
		pageLabel.setText("Seite " + String.valueOf(currentPage + 1) + "/" + pages.size());
	}

	private void nextPage() {
		if(currentPage < pages.size() -1) {
			currentPage++;
			page.getChildren().clear();
			page.getChildren().add(pages.get(currentPage));
			updateLabel();
		}
	}
	
	private void prevPage() {
		if(currentPage > 0) {
			currentPage--;
			page.getChildren().clear();
			page.getChildren().add(pages.get(currentPage));
			updateLabel();
		}
	}

}
