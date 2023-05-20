package com.example.organizer.CustomView;

import com.example.organizer.Controller.SciencesController;
import com.example.organizer.Main;
import com.example.organizer.model.Reminder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ReminderView extends AnchorPane {
    @FXML
    private Label lbName;
    @FXML
    private Label lbDate;
    @FXML
    private TextArea taQuest;
    @FXML
    private AnchorPane pane;

    public ReminderView(Reminder reminder) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(
                "reminder.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        pane.setOnMouseClicked(mouseEvent -> {
            SciencesController.toEditReminder(reminder);
        });
        this.lbName.setText(reminder.getLessonName());
        this.lbDate.setText(reminder.getDate());
        this.taQuest.setText(reminder.getQuest());
    }

}
