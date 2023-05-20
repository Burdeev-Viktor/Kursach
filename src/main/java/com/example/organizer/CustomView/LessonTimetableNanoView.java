package com.example.organizer.CustomView;

import com.example.organizer.Main;
import com.example.organizer.model.LessonTimetable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LessonTimetableNanoView extends AnchorPane {
    @FXML
    private Label lbName;
    @FXML
    private Label lbTime;
    @FXML
    private AnchorPane pane;

    public LessonTimetableNanoView(LessonTimetable lessonTimetable) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("lesson-timetable-nano.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        pane.setOnMouseClicked(mouseEvent -> {

        });
        this.lbTime.setText(lessonTimetable.getTime());
        this.lbName.setText(lessonTimetable.getName());
        switch (lessonTimetable.getType()) {
            case LECTURE -> this.pane.setStyle("-fx-background-color: #87b93c");
            case LAB -> this.pane.setStyle("-fx-background-color: #c9ac4cff");
            case PRACTICE -> this.pane.setStyle("-fx-background-color: #674cc9");
            case CONSULTATION -> this.pane.setStyle("-fx-background-color: #4cc5c9ff");
        }
    }
}
