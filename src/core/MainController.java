package core;

import entities.Answer;
import entities.Configuration;
import entities.Table;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pollElements.MCTable;


public class MainController extends Application{

	public static void main(String[] args) {
			launch(args);
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
	
			
	}

	@Override
	public void start(Stage stage) throws Exception {
		AnchorPane pane = new AnchorPane();
		pane.setPrefHeight(700);
		pane.setPrefWidth(700);
		Scene scene = new Scene(pane);
	
		MCTable table = new MCTable();
		table.setCols(4);
		table.setRows(3);
		table.setTableHeight(400);
		table.setTableWidth(400);
		table.setPositionX(200);
		table.setPositionY(100);
		table.generateNodes();
		table.generateContextMenus();
		table.reposition();
		table.generateHandlers();
		pane.getChildren().add(table);
		stage.setScene(scene);
		stage.show();
		
		Button button1 = new Button("+R");
		button1.setLayoutY(0);
		button1.setOnAction(e->{
			table.addRow();
		});
		Button button2 = new Button("-R");
		button2.setLayoutY(40);
		button2.setOnAction(e->{
			table.removeRow();
		});
		Button button3 = new Button("+C");
		button3.setLayoutY(80);
		
		button3.setOnAction(e->{
			table.addCol();
		});
		Button button4 = new Button("-C");
		button4.setLayoutY(120);
		
		button4.setOnAction(e->{
			table.removeCol();
		});
		
		Button button5 = new Button("T");
		button5.setLayoutY(160);
		button5.setOnAction(e->{
			Table t = table.getTable();
			for(Answer answer : t.getAnswers()) {
				System.out.println(answer.getAnswer());
				System.out.println(answer.getValue());
				System.out.println("-------------");
			}
		});
		pane.getChildren().addAll(button1,button2,button3,button4,button5);
	
	}
	
}
