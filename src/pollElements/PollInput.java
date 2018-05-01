package pollElements;

import entities.Input;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PollInput extends Group{

	private TextArea questionArea;
	private TextArea inputArea;
	private Rectangle qResize;
	private Rectangle iResize;
	private Rectangle qMove;
	private Rectangle iMove;
	private Rectangle boundsRec;
	
	//Dimensioning
	private double questionX;
	private double questionY;
	private double inputX;
	private double inputY;
	
	public PollInput() {
		
		qResize = new Rectangle();
		qResize.setWidth(6);
		qResize.setHeight(6);
		qResize.setFill(Color.LAWNGREEN);
		iResize = new Rectangle();
		iResize.setWidth(6);
		iResize.setHeight(6);
		iResize.setFill(Color.LAWNGREEN);
		qMove = new Rectangle();
		qMove.setWidth(6);
		qMove.setHeight(6);
		qMove.setFill(Color.BLUE);
		iMove = new Rectangle();
		iMove.setWidth(6);
		iMove.setHeight(6);
		iMove.setFill(Color.BLUE);
		boundsRec = new Rectangle();
		boundsRec.setFill(Color.GREY);
		
		//Generating Handlers
		//RESIZE
		qResize.setCursor(Cursor.SE_RESIZE);
		qResize.setOnMousePressed(e->{
			boundsRec.setLayoutX(questionArea.getLayoutX());
			boundsRec.setLayoutY(questionArea.getLayoutY());
			boundsRec.setHeight(questionArea.getPrefHeight());
			boundsRec.setWidth(questionArea.getPrefWidth());
			this.getChildren().clear();
			this.getChildren().addAll(boundsRec,qResize,inputArea);
		});
		
		qResize.setOnMouseDragged(e->{
			boundsRec.setHeight(e.getSceneY() - questionArea.getLayoutY());
			boundsRec.setWidth(e.getSceneX() - questionArea.getLayoutX());
			qResize.setLayoutX(e.getSceneX());
			qResize.setLayoutY(e.getSceneY());
		});
		
		qResize.setOnMouseReleased(e->{
			questionArea.setPrefWidth(boundsRec.getWidth());
			questionArea.setPrefHeight(boundsRec.getHeight());
			qMove.setLayoutX(questionArea.getLayoutX() + questionArea.getPrefWidth()/2);
			this.getChildren().clear();
			this.getChildren().addAll(questionArea,inputArea);
			this.getChildren().addAll(qResize,iResize,qMove,iMove);
		});
		
		
		iResize.setCursor(Cursor.SE_RESIZE);
		iResize.setOnMousePressed(e->{
			boundsRec.setLayoutX(inputArea.getLayoutX());
			boundsRec.setLayoutY(inputArea.getLayoutY());
			boundsRec.setHeight(inputArea.getPrefHeight());
			boundsRec.setWidth(inputArea.getPrefWidth());
			this.getChildren().clear();
			this.getChildren().addAll(boundsRec,iResize,questionArea);
		});
		
		iResize.setOnMouseDragged(e->{
			boundsRec.setHeight(e.getSceneY() - inputArea.getLayoutY());
			boundsRec.setWidth(e.getSceneX() - inputArea.getLayoutX());
			iResize.setLayoutX(e.getSceneX());			
			iResize.setLayoutY(e.getSceneY());
		});
		
		iResize.setOnMouseReleased(e->{
			inputArea.setPrefWidth(boundsRec.getWidth());
			inputArea.setPrefHeight(boundsRec.getHeight());
			iMove.setLayoutX(inputArea.getLayoutX() + inputArea.getPrefWidth()/2);
			this.getChildren().clear();
			this.getChildren().addAll(questionArea,inputArea);
			this.getChildren().addAll(qResize,iResize,qMove,iMove);
		});
		
		//MOVE
		qMove.setCursor(Cursor.MOVE);
		qMove.setOnMousePressed(e->{
			boundsRec.setLayoutX(questionArea.getLayoutX());
			boundsRec.setLayoutY(questionArea.getLayoutY());
			boundsRec.setHeight(questionArea.getPrefHeight());
			boundsRec.setWidth(questionArea.getPrefWidth());
			this.getChildren().clear();
			this.getChildren().addAll(boundsRec,qMove,inputArea);
		});
		
		qMove.setOnMouseDragged(e->{
			boundsRec.setLayoutX(e.getSceneX() - boundsRec.getWidth()/2);
			boundsRec.setLayoutY(e.getSceneY() + 6);
			qMove.setLayoutX(e.getSceneX());
			qMove.setLayoutY(e.getSceneY());
		});
		
		qMove.setOnMouseReleased(e->{
			questionArea.setLayoutX(boundsRec.getLayoutX());
			questionArea.setLayoutY(boundsRec.getLayoutY());
			qResize.setLayoutX(questionArea.getLayoutX() + questionArea.getPrefWidth());
			qResize.setLayoutY(questionArea.getLayoutY() + questionArea.getPrefHeight());
			this.getChildren().clear();
			this.getChildren().addAll(questionArea,inputArea);
			this.getChildren().addAll(qResize,iResize,qMove,iMove);
		});
		
		iMove.setCursor(Cursor.MOVE);
		iMove.setOnMousePressed(e->{
			boundsRec.setLayoutX(inputArea.getLayoutX());
			boundsRec.setLayoutY(inputArea.getLayoutY());
			boundsRec.setHeight(inputArea.getPrefHeight());
			boundsRec.setWidth(inputArea.getPrefWidth());
			this.getChildren().clear();
			this.getChildren().addAll(boundsRec,iMove,questionArea);
		});
		
		iMove.setOnMouseDragged(e->{
			boundsRec.setLayoutX(e.getSceneX() - boundsRec.getWidth()/2);
			boundsRec.setLayoutY(e.getSceneY() + 6);
			iMove.setLayoutX(e.getSceneX());
			iMove.setLayoutY(e.getSceneY());
		});
		
		iMove.setOnMouseReleased(e->{
			inputArea.setLayoutX(boundsRec.getLayoutX());
			inputArea.setLayoutY(boundsRec.getLayoutY());
			iResize.setLayoutX(inputArea.getLayoutX() + inputArea.getPrefWidth());
			iResize.setLayoutY(inputArea.getLayoutY() + inputArea.getPrefHeight());
			this.getChildren().clear();
			this.getChildren().addAll(questionArea,inputArea);
			this.getChildren().addAll(qResize,iResize,qMove,iMove);
		});
		
	}
	
	public void generateNodes() {
		questionArea = new TextArea();
		inputArea = new TextArea();
		questionArea.setWrapText(true);
		inputArea.setWrapText(true);
		questionArea.setPromptText("FRAGE");
		inputArea.setEditable(false);
		inputArea.setText("EINGABEFELD");
		
		questionArea.setLayoutX(questionX);
		questionArea.setLayoutY(questionY);
		questionArea.setPrefHeight(100);
		questionArea.setPrefWidth(100);
		
		inputArea.setLayoutX(inputX);
		inputArea.setLayoutY(inputY);
		inputArea.setPrefHeight(100);
		inputArea.setPrefWidth(100);
		
		qResize.setLayoutX(questionArea.getLayoutX() + questionArea.getPrefWidth());
		qResize.setLayoutY(questionArea.getLayoutY() + questionArea.getPrefHeight());
		iResize.setLayoutY(inputArea.getLayoutY() + inputArea.getPrefHeight());
		iResize.setLayoutX(inputArea.getLayoutX() + inputArea.getPrefWidth());
		qMove.setLayoutX(questionX + questionArea.getPrefWidth()/2);
		qMove.setLayoutY(questionY - 6);
		iMove.setLayoutX(inputArea.getLayoutX() + inputArea.getPrefWidth()/2);
		iMove.setLayoutY(inputY - 6);
		this.getChildren().addAll(questionArea,inputArea);
		this.getChildren().addAll(qResize,iResize,qMove,iMove);
	}
	
	public void setQuestionX(double questionX) {
		this.questionX = questionX;
		setInputX(questionX + 100);
	}

	public void setQuestionY(double questionY) {
		this.questionY = questionY;
		setInputY(questionY);
	}

	public void setInputX(double inputX) {
		this.inputX = inputX;
	}

	public void setInputY(double inputY) {
		this.inputY = inputY;
	}

	public Input getInput() {
		Input input = new Input();
		input.setQuestion(questionArea.getText());
		return input;
	}
}
