package com.example.organizer.controllers;

import com.example.organizer.Const;
import com.example.organizer.model.Lesson;
import com.example.organizer.model.enums.TypeOfTest;
import com.example.organizer.service.Session;
import com.example.organizer.service.LessonService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;



public class LessonsEditController implements Initializable {
    LessonService lessonService = new LessonService();
    @FXML
    private Button butSave;
    @FXML
    private Button butClose;
    @FXML
    private TextField twName;
    @FXML
    private ChoiceBox<String> cbType;
    @FXML
    private TextArea taConditions;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void setInfo(Lesson lesson){
        twName.setText(lesson.getName());
        cbType.getItems().addAll(Arrays.stream(TypeOfTest.values()).filter( type -> {
            return !Objects.equals(type, lesson.getTypeOfTest());
        }).map(typeOfTest -> { return typeOfTest.getType();}).toList());
        cbType.getItems().add(0,lesson.getTypeOfTest().getType());
        cbType.setValue(lesson.getTypeOfTest().getType());
        taConditions.setText(lesson.getCondition());
        butClose.setOnAction(SciencesController::closeThis);
        butSave.setOnAction(actionEvent -> {
            if(Objects.equals(twName.getText(), "")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(Const.MESSAGE_ERROR_NOT_LESSON_NAME);
                alert.show();
                return;
            }
            Lesson newLesson = new Lesson(twName.getText(), cbType.getValue(), taConditions.getText());
            newLesson.setIdUser(Session.getId());
            newLesson.setId(lesson.getId());
            System.out.println(newLesson.getName()+ "\n" +lesson.getIdUser() + "\n"+lesson.getId() + "\n"+lesson.getTypeOfTest()+ "\n"+lesson.getCondition());
            lessonService.save(newLesson);
            SciencesController.closeThis(actionEvent);
        });
    }
}
