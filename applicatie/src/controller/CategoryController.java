package controller;


import database.DatabaseService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Category;
import model.DomainException;
import view.panels.CategoryDetailPane;
import view.panels.CategoryOverviewPane;
import java.util.List;

public class CategoryController {
    private CategoryOverviewPane categoryOverviewPane;
    private CategoryDetailPane categoryDetailPane;
    private DatabaseService databaseService;
    private Stage stage;
    private List<Category> categoryList;
    private Boolean detailPaneOpen = false;

    public CategoryController(CategoryOverviewPane categoryOverviewPane) {
        this.categoryOverviewPane = categoryOverviewPane;

        this.categoryOverviewPane.setNewAction(new OpenDetailPane());

        this.categoryOverviewPane.setEditAction(new OpenDetailPaneEdit());
    }

    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
        categoryList = databaseService.readCategories();
        try {
            this.categoryOverviewPane.getTable().getItems().addAll(this.databaseService.readCategories());
        } catch (NullPointerException e) {
            e.fillInStackTrace();
            System.out.println("No categories yet");
        }
    }

    class OpenDetailPane implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if(detailPaneOpen == false){
                detailPaneOpen = true;
                stage = new Stage();
                categoryDetailPane = new CategoryDetailPane();
                Scene scene = new Scene(categoryDetailPane);
                stage.setScene(scene);
                try {
                    categoryDetailPane.getCategoryField().getItems().addAll(databaseService.getCategoryNamesWithoutDuplicates());
                } catch (NullPointerException e) {
                    e.fillInStackTrace();
                    System.out.println("No Category names yet");
                }
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
                categoryDetailPane.setSaveAction(new addCategory());
                categoryDetailPane.setCancelAction(new cancelCategory());
            }
        }
    }

    class addCategory implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {

            String name = categoryDetailPane.getTitleField().getText();
            String description = categoryDetailPane.getDescriptionField().getText();


            Category category;
            try {
                category = new Category(name, description);


            } catch (DomainException e) {
                category = new Category(categoryDetailPane.getCategoryField().getValue().toString(), description);
            }
            categoryOverviewPane.getTable().getItems().addAll(category);
            categoryList.add(category);
            databaseService.writeCategories(categoryList);

            stage.close();
            detailPaneOpen = false;
        }
    }

    class cancelCategory implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            stage.close();
            detailPaneOpen = false;
        }
    }

    class OpenDetailPaneEdit implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if(detailPaneOpen == false){
                detailPaneOpen = true;
                stage = new Stage();
                categoryDetailPane = new CategoryDetailPane();
                Category category = categoryOverviewPane.getSelectedRow();
                categoryDetailPane.setTitleField(category.getName());
                categoryDetailPane.setDescriptionField(category.getDescription());
                Scene scene = new Scene(categoryDetailPane);
                stage.setScene(scene);
                try {
                    categoryDetailPane.getCategoryField().getItems().addAll(databaseService.getCategoryNamesWithoutDuplicates());
                } catch (NullPointerException e) {
                    e.fillInStackTrace();
                    System.out.println("No Category names yet");
                }
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();

                categoryDetailPane.setSaveAction(new editCategory(category));
                categoryDetailPane.setCancelAction(new cancelCategory());
            }
        }
    }

    class editCategory implements EventHandler<ActionEvent> {
        Category cat;

        editCategory(Category category) {
            super();
            cat = category;
        }

        @Override
        public void handle(ActionEvent event) {
            String name = categoryDetailPane.getTitleField().getText();
            String description = categoryDetailPane.getDescriptionField().getText();

            cat.setDescription(description);
            cat.setName(name);
            try {
                categoryOverviewPane.getTable().getItems().addAll(databaseService.getCategoryDescriptions());
            } catch (NullPointerException e) {
                e.fillInStackTrace();
                System.out.println("No categories yet");
            }
            detailPaneOpen = false;
            stage.close();
        }
    }
}





