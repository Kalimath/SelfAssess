package controller;

import database.DatabaseService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Question;
import model.Test;
import view.panels.MessagePane;
import view.panels.TestPane;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TestController {
    private TestPane testPane;
    private MessagePane messagePane;
    private Stage stage;
    private DatabaseService databaseService;
    private Test test;
    private boolean updated = false;


    public TestController(MessagePane messagePane) {

        this.messagePane = messagePane;

        messagePane.getTestButton().setOnAction(new EvaluateTest());
    }

    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
        test = new Test(this.databaseService.readQuestions());
        try {
            if (databaseService.testIsCompleted()) {
                this.messagePane.getLabel().setText("You already completed this test\n\n"+ this.databaseService.getLastTestScores());
            } else {
                this.messagePane.getLabel().setText("You never did this evaluation");
            }
        } catch (NullPointerException e) {
            System.out.println("Geen test scores");
        }
    }

    class EvaluateTest implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            stage = new Stage();
            testPane = new TestPane();
            Scene scene = new Scene(testPane);
            stage.setScene(scene);
            testPane.setStatementGroup(new ToggleGroup());
            if (updated == false) {
                test.setQuestions(databaseService.readQuestions());
                updated = true;
            }
            try {
                Question question = test.findNextQuestion();

                testPane.getQuestionField().setText(question.getName());

                List<String> shuffledStatements = question.getStatements();
                Collections.shuffle(shuffledStatements);
                for (String answer : shuffledStatements) {
                    testPane.getvBox().getChildren().addAll(createButton(answer, testPane.getStatementGroup()));
                }

                testPane.setProcessAnswerAction(new SubmitTest());
                stage.show();
            } catch (NullPointerException e) {
                messagePane.getLabel().setText(test.printResults());

                try {
                    databaseService.setTestIsCompleted(true);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    databaseService.setLastTestScores(test.printResults());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                test = new Test(databaseService.readQuestions());
            }
        }
    }

    private RadioButton createButton(String text, ToggleGroup group) {
        RadioButton buttonPanelForSymbols = new RadioButton(text);
        buttonPanelForSymbols.setToggleGroup(group);
        return buttonPanelForSymbols;
    }

    class SubmitTest implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            RadioButton selectedRadioButton = (RadioButton) testPane.getStatementGroup().getSelectedToggle();
            String toggleGroupValue = selectedRadioButton.getText();
            if (!test.checkAnswer(toggleGroupValue)) {
                messagePane.getLabel().setText(test.getCurrentQuestion().getFeedback());
                stage.close();
            } else {
                messagePane.getLabel().setText("Correct !");
                stage.close();
            }
            messagePane.getTestButton().fire();
        }
    }
}
