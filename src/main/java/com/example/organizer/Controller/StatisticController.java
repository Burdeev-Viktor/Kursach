package com.example.organizer.Controller;

import com.example.organizer.CustomView.GtStatisticView;
import com.example.organizer.CustomView.LtStatisticView;
import com.example.organizer.model.Reminder;
import com.example.organizer.service.ReminderService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class StatisticController implements Initializable {
    private final ReminderService reminderService = new ReminderService();

    @FXML
    private HBox hLBox;
    @FXML
    private HBox hGBox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Reminder> labs = reminderService.getLabs();
        hLBox.getChildren().addAll(labs.stream().map(LtStatisticView::new).toList());
        hGBox.getChildren().addAll(labs.stream().map(GtStatisticView::new).toList());
    }
}
