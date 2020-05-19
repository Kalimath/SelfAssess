package view.panels;

import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MessagePane extends GridPane {
	private Button testButton;
	private Label label;
	
	public MessagePane (){
	    setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        label = new Label();
		add(label, 0, 0);
		testButton = new Button("Evaluate");
		add(testButton, 0,2,1,1);
		setHalignment(testButton, HPos.CENTER);
	}

	public Button getTestButton() {
		return testButton;
	}

	public Label getLabel() {
		return label;
	}

}
