package view.panels;

import controller.QuestionController;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Category;

import java.util.*;

public class QuestionDetailPane extends GridPane {
	private Button btnOK, btnCancel;
	private TextField questionField, statementField, feedbackField;
	private Button btnAdd, btnRemove;
	private ComboBox categoryField;
    private ListView<String> statementsArea;

	public QuestionDetailPane() {
		this.setPrefHeight(300);
		this.setPrefWidth(320);
		this.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        
		add(new Label("Question: "), 0, 0, 1, 1);
		questionField = new TextField();
		add(questionField, 1, 0, 2, 1);
		
		add(new Label("Statement: "), 0, 1, 1, 1);
		statementField = new TextField();
		add(statementField, 1, 1, 2, 1);

		add(new Label("Statements: "), 0, 2, 1, 1);
		statementsArea = new ListView<>();
		statementsArea.setEditable(false);
		add(statementsArea, 1, 2, 2, 5);

		Pane addRemove = new HBox();
		btnAdd = new Button("add");
		addRemove.getChildren().add(btnAdd);

		btnRemove = new Button("remove");
		addRemove.getChildren().add(btnRemove);
		add(addRemove, 1, 8, 2, 1);

		add(new Label("Category: "), 0, 9, 1, 1);
		categoryField = new ComboBox();
		add(categoryField, 1, 9, 2, 1);

		add(new Label("Feedback: "), 0, 10, 1, 1);
		feedbackField = new TextField();
		add(feedbackField, 1, 10, 2, 1);

		btnCancel = new Button("Cancel");
		btnCancel.setText("Cancel");
		add(btnCancel, 0, 11, 1, 1);

		btnOK = new Button("Save");
		btnOK.isDefaultButton();
		btnOK.setText("Save");
		add(btnOK, 1, 11, 2, 1);
		
	}

    public Button getBtnAdd() {
        return btnAdd;
    }

    public Button getBtnCancel() {
        return btnCancel;
    }

    public Button getBtnOK() {
        return btnOK;
    }

    public Button getBtnRemove() {
        return btnRemove;
    }


    public TextField getQuestionField() {
		return questionField;
	}

	public void setQuestionField(String text){questionField.setText(text);}

	public ComboBox getCategoryField() {
		return categoryField;
	}

	public void setCategoryField(String category){categoryField.getSelectionModel().select(category);}

	public ListView<String> getStatementsArea() {
		return statementsArea;
	}

	public void setStatementsArea(List<String> list){
		ObservableList<String> out = FXCollections.observableList(list);
		statementsArea.setItems(out);
	}

	public TextField getFeedbackField() {
		return feedbackField;
	}

	public void setFeedbackField(String text){feedbackField.setText(text);}

	public TextField getStatementField() {
		return statementField;
	}

	public void setSaveAction(EventHandler<ActionEvent> saveAction) {
		btnOK.setOnAction(saveAction);
	}

	public void setCancelAction(EventHandler<ActionEvent> cancelAction) {
		btnCancel.setOnAction(cancelAction);
	}


}
