package view.panels;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TestPane extends GridPane {
	private Label questionField;
	private Button submitButton;
	private ToggleGroup statementGroup;
	private VBox vBox;
	
	public TestPane (){
		this.setPrefHeight(300);
		this.setPrefWidth(750);
		
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

		questionField = new Label("Question");
		add(questionField, 0, 0, 1, 1);

		vBox = new VBox();
		add(vBox, 0, 2, 1, 1);

		submitButton = new Button("Submit");
		add(submitButton, 0, 20, 1, 1);
	}
	
	public void setProcessAnswerAction(EventHandler<ActionEvent> processAnswerAction) {
		submitButton.setOnAction(processAnswerAction);
	}

	public List<String> getSelectedStatements() {
		 List<String> selected = new ArrayList<String>();
		if(statementGroup.getSelectedToggle()!=null){
			selected.add(statementGroup.getSelectedToggle().getUserData().toString());
		}
		return selected;
	}

	public Label getQuestionField() {
		return questionField;
	}

	public Button getSubmitButton() {
		return submitButton;
	}

	public ToggleGroup getStatementGroup() {
		return statementGroup;
	}

	public void setStatementGroup(ToggleGroup statementGroup) {
		this.statementGroup = statementGroup;
	}

	public VBox getvBox() {
		return vBox;
	}
}
