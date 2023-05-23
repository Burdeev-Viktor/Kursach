package com.example.organizer.CustomView;

import com.example.organizer.Main;
import com.example.organizer.model.Reminder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;


import java.io.IOException;

public class LtStatisticView extends AnchorPane {
    @FXML
    private Label lbName;
    @FXML
    private Label lbprosent;
    @FXML
    private AnchorPane bScale;
    private float height;
    @FXML
    private Pane dScale;
    public LtStatisticView(Reminder reminder){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ltStatistic-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.lbName.setText(reminder.getLessonName());
        calHeight(reminder);
        calColor();
    }
    private void calHeight(Reminder reminder){
        if(reminder.getCloseWork()==0){
            dScale.setStyle("-fx-pref-height:0;-fx-background-radius:10");
            height = 0;
            lbprosent.setText( "0%");
            return;
        }
        height = (float)reminder.getCloseWork() / reminder.getNeedWork() ;
        System.out.println(reminder.getLessonName() + ((int) (250 * height)));
        lbprosent.setText((((int)(height * 100)) + "%"));
        dScale.setPrefHeight(((int) (250 * height)));
    }
    private void calColor(){
        if(0 <= height && height <= 0.25){
            dScale.setStyle("-fx-background-color: #bd2525 ;-fx-background-radius:10");
        }else if(0.25 < height && height <= 0.5){
            dScale.setStyle("-fx-background-color: #cb8c22 ;-fx-background-radius:10");
        }else if(0.5 < height && height <= 0.75){
            dScale.setStyle("-fx-background-color: #cccc15 ;-fx-background-radius:10");
        }else {
            dScale.setStyle("-fx-background-color: #15bd15 ;-fx-background-radius:10");
        }
    }
}
