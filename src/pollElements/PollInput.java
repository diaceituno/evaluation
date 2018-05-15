package pollElements;

import entities.Input;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
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
	private AnchorPane parent;
	
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
			double realX = e.getSceneX() - parent.getLayoutX();
			double realY = e.getSceneY() - parent.getLayoutY();
			double maxX = parent.getPrefWidth();
			double minX = questionArea.getLayoutX() + 50;
			double maxY = parent.getPrefHeight();
			double minY = questionArea.getLayoutY() + 50;
			
			if((realX > minX)&&(realX < maxX)) {
				boundsRec.setWidth(realX - questionArea.getLayoutX());
				qResize.setLayoutX(realX);
			}
			if((realY > minY)&&(realY < maxY)) {
				boundsRec.setHeight(realY - questionArea.getLayoutY());
				qResize.setLayoutY(realY);
			}
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
			double realX = e.getSceneX() - parent.getLayoutX();
			double realY = e.getSceneY() - parent.getLayoutY();
			double maxX = parent.getPrefWidth();
			double minX = inputArea.getLayoutX() + 50;
			double maxY = parent.getPrefHeight();
			double minY = inputArea.getLayoutY() + 50;
			
			if((realX > minX)&&(realX < maxX)) {
				boundsRec.setWidth(realX - inputArea.getLayoutX());
				iResize.setLayoutX(realX);
			}
			if((realY > minY)&&(realY < maxY)) {
				boundsRec.setHeight(realY - inputArea.getLayoutY());
				iResize.setLayoutY(realY);
			}
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
			
			double realX = e.getSceneX() - parent.getLayoutX();
			double realY = e.getSceneY() - parent.getLayoutY();
			double minX = questionArea.getPrefWidth()/2;
			double maxX = parent.getPrefWidth() - questionArea.getPrefWidth()/2;
			double minY = 0;
			double maxY = parent.getPrefHeight() - questionArea.getPrefHeight() - 6;
			
			if((realX > minX)&&(realX < maxX)) {
				qMove.setLayoutX(realX);
				boundsRec.setLayoutX(realX - boundsRec.getWidth()/2);
			}
			if((realY > minY)&&(realY < maxY)) {
				qMove.setLayoutY(realY);
				boundsRec.setLayoutY(realY + 6);
			}
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
			
			double realX = e.getSceneX() - parent.getLayoutX();
			double realY = e.getSceneY() - parent.getLayoutY();
			double minX = inputArea.getPrefWidth()/2;
			double maxX = parent.getPrefWidth() - inputArea.getPrefWidth()/2;
			double minY = 0;
			double maxY = parent.getPrefHeight() - inputArea.getPrefHeight() - 6;
			
			if((realX > minX)&&(realX < maxX)) {
				iMove.setLayoutX(realX);
				boundsRec.setLayoutX(realX - boundsRec.getWidth()/2);
			}
			if((realY > minY)&&(realY < maxY)) {
				iMove.setLayoutY(realY);
				boundsRec.setLayoutY(realY + 6);
			}
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
	
	public PollInput duplicate() {
		
		PollInput input = new PollInput();
		input.generateNodes();
		TextArea question = input.getQuestion();
		TextArea in = input.getInputArea();
		Rectangle qMov = input.getQMov();
		Rectangle iMov = input.getIMove();
		Rectangle qRes = input.getQres();
		Rectangle iRes = input.getIRes();
		
		question.setPrefHeight(questionArea.getPrefHeight());
		question.setPrefWidth(questionArea.getPrefWidth());
		question.setLayoutX(questionArea.getLayoutX());
		question.setLayoutY(questionArea.getLayoutY());
		question.setText(questionArea.getText());
		in.setPrefHeight(inputArea.getPrefHeight());
		in.setPrefWidth(inputArea.getPrefWidth());
		in.setLayoutX(inputArea.getLayoutX());
		in.setLayoutY(inputArea.getLayoutY());
		
		qMov.setLayoutX(qMove.getLayoutX());
		qMov.setLayoutY(qMove.getLayoutY());
		qRes.setLayoutX(qResize.getLayoutX());
		qRes.setLayoutY(qResize.getLayoutY());
		
		iMov.setLayoutX(iMove.getLayoutX());
		iMov.setLayoutY(iMove.getLayoutY());
		iRes.setLayoutX(iResize.getLayoutX());
		iRes.setLayoutY(iResize.getLayoutY());
		
		return input;
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
		this.questionX = questionX - parent.getLayoutX();
		setInputX(this.questionX + 100);
	}

	public void setQuestionY(double questionY) {
		this.questionY = questionY - parent.getLayoutY();
		setInputY(this.questionY);
	}

	public void setInputX(double inputX) {
		this.inputX = inputX;
	}

	public void setInputY(double inputY) {
		this.inputY = inputY;
	}

	public void setParent(AnchorPane parent) {
		this.parent = parent;
	}
	
	public Input getInput() {
		Input input = new Input();
		input.setQuestion(questionArea.getText());
		return input;
	}

	public TextArea getQuestion() {
		return questionArea;
	}
	
	public TextArea getInputArea() {
		return inputArea;
	}
	
	public Rectangle getQMov() {
		return qMove;
	}
	
	public Rectangle getQres() {
		return qResize;
	}

	public Rectangle getIMove() {
		return iMove;
	}
	
	public Rectangle getIRes() {
		return iResize;
	}
}
