package com.example.organizer.customView;

import com.example.organizer.Main;
import com.example.organizer.model.Grade;
import com.example.organizer.model.Reminder;
import com.example.organizer.service.GradeService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.List;

public class GtStatisticView extends AnchorPane {
    private static final GradeService gradeService = new GradeService();
    @FXML
    private Label lbName;
    @FXML
    private Label lbGrade;
    @FXML
    private AnchorPane bScale;
    private float height;
    @FXML
    private Pane dScale;
    public GtStatisticView(Reminder reminder) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("gtStatistic-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        calGrade(reminder);
        this.lbName.setText(reminder.getLessonName());
    }
    private void calGrade(Reminder reminder){
        List<Grade> gradeList = gradeService.getGradesByReminder(reminder);
        if (gradeList.size() == 0){
            dScale.setStyle("-fx-background-color: #bd2525 ;-fx-background-radius:10");
            lbGrade.setText("0.0");
            return;
        }
        long sum = gradeList.stream().mapToLong(Grade::getGrade).sum();
        double mean = (double) sum / gradeList.size();
        lbGrade.setText(String.valueOf(mean));
        height = (float) (mean / 10);
        dScale.setPrefHeight(((int) (296 * height)));
        if(0 <= mean && mean <= 2.5){
            dScale.setStyle("-fx-background-color: #bd2525 ;-fx-background-radius:10");
        }else if(2.5 < mean && mean <= 5){
            dScale.setStyle("-fx-background-color: #cb8c22 ;-fx-background-radius:10");
        }else if(5 < mean && mean <= 7.5){
            dScale.setStyle("-fx-background-color: #cccc15 ;-fx-background-radius:10");
        }else {
            dScale.setStyle("-fx-background-color: #15bd15 ;-fx-background-radius:10");
        }
    }
}
