package com.example.organizer.CustomView;

import com.example.organizer.Main;
import com.example.organizer.model.Lesson;
import com.example.organizer.service.LessonService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LessonView extends AnchorPane {
    private final LessonService lessonService = new LessonService();
    @FXML
    private Label lbName;
    @FXML
    private Button butDel;
    @FXML
    private AnchorPane pane;
    @FXML
    private Stage stage;

    public LessonView(Lesson lesson) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("lesson-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        pane.setOnMouseClicked(mouseEvent -> {

        });
        butDel.setOnAction(event -> {
            lessonService.delete(lesson);
            this.setVisible(false);
            this.setDisable(true);
            this.setHeight(0);
        });
        this.lbName.setText(lesson.getName());
        switch (lesson.getTypeOfTest()) {
            case "Экзамен" -> this.pane.setStyle("-fx-background-color: #c94c4c");
            case "Зачёт" -> this.pane.setStyle("-fx-background-color: #c9ac4cff");
            case "Тест" -> this.pane.setStyle("-fx-background-color: #674cc9");
            case "Неизвестно" -> this.pane.setStyle("-fx-background-color: #4cc5c9ff");
        }
    }
}
