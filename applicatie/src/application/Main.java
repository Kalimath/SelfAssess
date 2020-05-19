package application;

import controller.CategoryController;
import controller.QuestionController;
import controller.TestController;
import database.DatabaseService;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Question;
import view.panels.AssesMainPane;
import view.panels.CategoryDetailPane;
import view.panels.CategoryOverviewPane;
import view.panels.MessagePane;
import view.panels.QuestionDetailPane;
import view.panels.QuestionOverviewPane;
import view.panels.TestPane;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		try {

			//hier moet de strategy in komen via de properties path
			DatabaseService databaseService = new DatabaseService();
			/*List<String> statementstest = new ArrayList<>();
			statementstest.add("juiste antwoord");
			statementstest.add("fout antwoord 1");
			statementstest.add("fout antwoord 2");
			Question testeen = new Question("eerste vraag","category1",statementstest,"feedback1");
			Question testtwee = new Question("tweede vraag","category2",statementstest,"feedback2");
			Question testdrie = new Question("derde vraag","category3",statementstest,"feedback3");
			ArrayList<Question> testje = new ArrayList<>();
			testje.add(testeen);
			testje.add(testtwee);
			testje.add(testdrie);
			databaseService.writeQuestions(testje);
			for(Question q : databaseService.readQuestions()){
				System.out.println("vraag: "+ q.getName()+q.getCategory()+q.getStatements().size()+q.getFeedback());
			}
			System.out.println("catdesc length: "+databaseService.getCategoryDescriptions().size());*/


			QuestionOverviewPane questionOverviewPane = new QuestionOverviewPane();
			QuestionDetailPane questionDetailPane = new QuestionDetailPane();

			CategoryOverviewPane categoryOverviewPane = new CategoryOverviewPane();
			CategoryDetailPane categoryDetailPanel = new CategoryDetailPane();

			CategoryController categoryController = new CategoryController(categoryOverviewPane);
			categoryController.setDatabaseService(databaseService);
            QuestionController questionController = new QuestionController(questionOverviewPane);
            questionController.setDatabaseService(databaseService);

			TestPane testPane = new TestPane();
			MessagePane messagePane = new MessagePane();
            TestController testController = new TestController(messagePane);
            testController.setDatabaseService(databaseService);

			Group root = new Group();
			Scene scene = new Scene(root, 750, 400);

			BorderPane borderPane = new AssesMainPane(messagePane, categoryOverviewPane, questionOverviewPane);
			borderPane.prefHeightProperty().bind(scene.heightProperty());
			borderPane.prefWidthProperty().bind(scene.widthProperty());

			root.getChildren().add(borderPane);
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
