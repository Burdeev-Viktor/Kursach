package com.example.organizer.Controller;

import com.example.organizer.Const;
import com.example.organizer.CustomView.LessonView;
import com.example.organizer.Repositories.LessonRepo;
import com.example.organizer.Repositories.ReminderRepo;
import com.example.organizer.Service.ThisUser;
import com.example.organizer.model.Lesson;
import com.example.organizer.model.Reminder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class LessonsController implements Initializable {
    @FXML
    private Button butAdd;
    @FXML
    private Button butClose;
    @FXML
    private VBox vbLessons;
    @FXML
    private TextField twName;
    @FXML
    private TextField twCountLab;
    @FXML
    private CheckBox checkBox;
    @FXML
    private ChoiceBox<String> cbType;
    @FXML
    private TextArea taConditions;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void setInfo(){
        ArrayList<Lesson> lessons = LessonRepo.getAllLessonsByUserId(ThisUser.getId());
        for (Lesson lesson : lessons) {
            vbLessons.getChildren().add(new LessonView(lesson));
        }
        cbType.getItems().addAll(Const.CHOICE_BOX_TYPE_OF_TEST);
        cbType.setValue(Const.CHOICE_BOX_TYPE_OF_TEST[3]);
        butClose.setOnAction(event -> {
            if(LessonRepo.getAllLessonsByUserId(ThisUser.getId()).size() == 0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(Const.MESSAGE_ERROR_NOT_LESSONS_IN_db);
                alert.show();
                return;
            }
            SciencesController.toMain(event,ThisUser.getId());
        });
        butAdd.setOnAction(event -> {
            if(Objects.equals(twName.getText(), "")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(Const.MESSAGE_ERROR_NOT_LESSON_NAME);
                alert.show();
                return;
            }
            if(LessonRepo.lessonIsExistsByUser(twName.getText(),ThisUser.getId())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(Const.MESSAGE_ERROR_LESSON_NAME_IS_EXSIST);
                alert.show();
                return;
            }
            Lesson newLesson = new Lesson(twName.getText(), cbType.getValue(), taConditions.getText());
            newLesson.setIdUser(ThisUser.getId());
            LessonRepo.addLessonByIdUser(newLesson);
            SciencesController.toLessons(event);
            if(checkBox.isSelected()){
                Reminder reminder = new Reminder();
                if(ReminderRepo.reminderTableIsExists()){
                    ReminderRepo.createReminderTable();
                }
                reminder.setNeedWork(Integer.parseInt(twCountLab.getText()));
                reminder.setSwitchR(false);
                reminder.setLessonName(twName.getText());
                reminder.setQuest("Лабораторные рыботы");
                reminder.setIdUser(ThisUser.getId());
                ReminderRepo.addReminderByIdUser(reminder);
            }
        });
        checkBox.setOnAction(event -> {
            twCountLab.setDisable(!checkBox.isSelected());
        });
        twCountLab.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    twCountLab.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
