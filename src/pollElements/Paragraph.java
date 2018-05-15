package pollElements;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Paragraph extends Group{

	private TextArea area;
	private Rectangle movRec;
	private Rectangle resRec;
	private Rectangle boundsRec;
	private AnchorPane parent;
	
	public Paragraph() {
		
		area = new TextArea();
		area.setPrefWidth(100);
		area.setPrefHeight(100);
		area.setWrapText(true);
		
		movRec = new Rectangle();
		movRec.setHeight(6);
		movRec.setWidth(6);
		movRec.setFill(Color.BLUE);
		movRec.setCursor(Cursor.MOVE);
		
		resRec = new Rectangle();
		resRec.setHeight(6);
		resRec.setWidth(6);
		resRec.setFill(Color.LIGHTGREEN);
		resRec.setCursor(Cursor.SE_RESIZE);
		
		boundsRec = new Rectangle();
		boundsRec.setFill(Color.GREY);
		
		
		movRec.setOnMousePressed(e->{
			boundsRec.setHeight(area.getPrefHeight());
			boundsRec.setWidth(area.getPrefWidth());
			boundsRec.setLayoutX(area.getLayoutX());
			boundsRec.setLayoutY(area.getLayoutY());
			this.getChildren().clear();
			this.getChildren().addAll(boundsRec,movRec);
		});
		
		movRec.setOnMouseDragged(e->{
			double realX = e.getSceneX() - parent.getLayoutX();
			double realY = e.getSceneY() - parent.getLayoutY();
			double margin = boundsRec.getWidth()/2;
			double maxX = parent.getPrefWidth() - margin;
			double maxY = parent.getPrefHeight() - boundsRec.getHeight() - 6;
			double minX = margin;
			double minY = 0;
			
			if((realX > minX)&&(realX < maxX)){
				movRec.setLayoutX(realX);
				boundsRec.setLayoutX(realX - margin);
			}
			if((realY > minY)&&(realY < maxY)) {
				movRec.setLayoutY(realY);
				boundsRec.setLayoutY(realY + 6);
			}
		});
		
		movRec.setOnMouseReleased(e->{
			this.getChildren().clear();
			area.setLayoutX(boundsRec.getLayoutX());
			area.setLayoutY(boundsRec.getLayoutY());
			resRec.setLayoutX(area.getLayoutX() + area.getPrefWidth());
			resRec.setLayoutY(area.getLayoutY() + area.getPrefHeight());
			this.getChildren().addAll(area,movRec,resRec);
		});
		
		resRec.setOnMousePressed(e->{
			boundsRec.setHeight(area.getPrefHeight());
			boundsRec.setWidth(area.getPrefWidth());
			boundsRec.setLayoutX(area.getLayoutX());
			boundsRec.setLayoutY(area.getLayoutY());
			this.getChildren().clear();
			this.getChildren().addAll(boundsRec,resRec);
		});
		
		resRec.setOnMouseDragged(e->{	
			
			double realX = e.getSceneX() - parent.getLayoutX();
			double realY = e.getSceneY() - parent.getLayoutY();
			double maxX = parent.getPrefWidth();
			double maxY = parent.getPrefHeight();
			double minX = boundsRec.getLayoutX() + 50;
			double minY = boundsRec.getLayoutY() + 50;
			
			if((realX > minX)&&(realX < maxX)) {
				resRec.setLayoutX(realX);
				boundsRec.setWidth(realX - boundsRec.getLayoutX());
			}
			if((realY > minY)&&(realY < maxY)) {
				resRec.setLayoutY(realY);
				boundsRec.setHeight(realY - boundsRec.getLayoutY());
			}
			
			
			
		});
		
		resRec.setOnMouseReleased(e->{
			this.getChildren().clear();
			area.setPrefHeight(boundsRec.getHeight());
			area.setPrefWidth(boundsRec.getWidth());
			movRec.setLayoutX(area.getLayoutX() + area.getPrefWidth()/2);
			movRec.setLayoutY(area.getLayoutY() - 6);
			this.getChildren().addAll(area,movRec,resRec);
		});
		
		this.getChildren().addAll(area,movRec,resRec);
	}
	
	public Paragraph duplicate() {
		
		Paragraph paragraph = new Paragraph();
		
		TextArea area = paragraph.getArea();
		Rectangle movRec = paragraph.getMov();
		Rectangle resRec = paragraph.getRes();
		
		area.setPrefWidth(this.area.getPrefWidth());
		area.setPrefHeight(this.area.getPrefHeight());
		area.setLayoutX(this.area.getLayoutX());
		area.setLayoutY(this.area.getLayoutY());
		area.setText(this.area.getText());
		
		movRec.setLayoutX(this.movRec.getLayoutX());
		movRec.setLayoutY(this.movRec.getLayoutY());
		
		resRec.setLayoutX(this.resRec.getLayoutX());
		resRec.setLayoutY(this.resRec.getLayoutY());
		
		return paragraph;
	}
	
	public void setX(double areaX) {
		area.setLayoutX(areaX - parent.getLayoutX());
		position();
	}
	
	public void setY(double areaY) {
		area.setLayoutY(areaY - parent.getLayoutY());
		position();
	}
	
	private void position() {
		movRec.setLayoutX(area.getLayoutX() + area.getPrefWidth()/2);
		movRec.setLayoutY(area.getLayoutY() - 6);
		resRec.setLayoutX(area.getLayoutX() + area.getPrefWidth());
		resRec.setLayoutY(area.getLayoutY() + area.getPrefHeight());
	}

	public void setParent(AnchorPane parent) {
		this.parent = parent;
	}

	public TextArea getArea() {
		return area;
	}
	
	public Rectangle getMov() {
		return movRec;
	}
	
	public Rectangle getRes() {
		return resRec;
	}
}
