package pollElements;

import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Rectangle;

public class PollInput extends Group{

	private TextArea questionArea;
	private TextArea inputArea;
	private Rectangle qResize;
	private Rectangle iResize;
	private Rectangle qMove;
	private Rectangle iMove;
	
	public PollInput() {
		
		questionArea = new TextArea();
		inputArea = new TextArea();
		questionArea.setPromptText("FRAGE");
		inputArea.setEditable(false);
	}
	
	
	
}
