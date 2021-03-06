package view.panels;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.awt.*;

public class CategoryDetailPane extends GridPane{
    private Button btnOK, btnCancel;
    private TextField titleField, descriptionField;
    private ComboBox categoryField;

    public CategoryDetailPane() {
        this.setPrefHeight(150);
        this.setPrefWidth(300);

        this.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.add(new Label("Title:"), 0, 0, 1, 1);
        titleField = new TextField();
        this.add(titleField, 1, 0, 1, 1);

        this.add(new Label("Description:"), 0, 1, 1, 1);
        descriptionField = new TextField();
        this.add(descriptionField, 1, 1, 1, 1);

        btnCancel = new Button("Cancel");
        this.add(btnCancel, 0, 3, 1, 1);

        btnOK = new Button("Save");
        btnOK.isDefaultButton();
        this.add(btnOK, 1, 3, 1, 1);

    }

    public TextField getTitleField() {
        return titleField;
    }

    public void setTitleField(String title){titleField.setText(title);}

    public TextField getDescriptionField() {
        return descriptionField;
    }

    public void setDescriptionField(String description){descriptionField.setText(description);}

    public ComboBox getCategoryField() {
        return categoryField;
    }

    public void setSaveAction(EventHandler<ActionEvent> saveAction) {
        btnOK.setOnAction(saveAction);
    }

    public void setCancelAction(EventHandler<ActionEvent> cancelAction) {
        btnCancel.setOnAction(cancelAction);
    }

}
