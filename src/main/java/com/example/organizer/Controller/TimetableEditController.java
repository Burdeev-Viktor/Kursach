package com.example.organizer.Controller;


import com.example.organizer.CustomView.LessonTimetableSmallView;
import com.example.organizer.repository.Session;
import com.example.organizer.model.LessonTimetable;
import com.example.organizer.service.TimetableService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TimetableEditController implements Initializable {

    @FXML
    private Button butAdd;
    @FXML
    private Button butToMain;
    @FXML
    private VBox vb00;
    @FXML
    private VBox vb01;
    @FXML
    private VBox vb02;
    @FXML
    private VBox vb03;
    @FXML
    private VBox vb04;
    @FXML
    private VBox vb05;
    @FXML
    private VBox vb10;
    @FXML
    private VBox vb11;
    @FXML
    private VBox vb12;
    @FXML
    private VBox vb13;
    @FXML
    private VBox vb14;
    @FXML
    private VBox vb15;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setInfo();
        butAdd.setOnAction(SciencesController::toAddLesson);
        butToMain.setOnAction(event -> SciencesController.toMain(event, Session.getId()));
    }
    public void setInfo() {
        VBox[][] vBoxes = {
                {vb00, vb01, vb02, vb03, vb04, vb05},
                {vb10, vb11, vb12, vb13, vb14, vb15}
        };
        LessonTimetable [][][] lessonTimetables = TimetableService.getSortLessonsTimetableAll();
        for(int w = 0; w < vBoxes.length; w++){
            for (int d = 0; d < vBoxes[0].length;d++){
                if(lessonTimetables[w][d] == null){
                    continue;
                }
                for (int i = 0; i < lessonTimetables[w][d].length; i++){
                    vBoxes[w][d].getChildren().add(new LessonTimetableSmallView(lessonTimetables[w][d][i]));
                    vBoxes[w][d].setSpacing(5);
                }
            }
        }
    }

}
