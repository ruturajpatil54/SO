/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import backgroundtasks.Task1;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.Installer;

/**
 * FXML Controller class
 *
 * @author ruturaj
 */
public class ProgressController implements Initializable {
    public static ProgressController instance;
    @FXML
    private AnchorPane anchorMain;
    @FXML
    private Label lblPrompt;
    @FXML
    private Color x3;
    @FXML
    private Group progressBars;
    @FXML
    private ImageView imgDone1;
    @FXML
    private ProgressIndicator progress1;
    @FXML
    private Label lblOne;
    @FXML
    private Font x1;
    @FXML
    private ImageView imgDone2;
    @FXML
    private ProgressIndicator progress2;
    @FXML
    private Label lblTwo;
    @FXML
    private ImageView imgDone3;
    @FXML
    private ProgressIndicator progress3;
    @FXML
    private Label lblThree;
    @FXML
    private ImageView imgDone4;
    @FXML
    private ProgressIndicator progress4;
    @FXML
    private Label lblFour;
    @FXML
    private Button btnProceed;
    @FXML
    private Font x2;
    @FXML
    private ProgressBar progressOverall;
    @FXML
    private ProgressBar progressCurrent;
    @FXML
    private Accordion accordian;
    @FXML
    private TitledPane titledPaneConsole;
    @FXML
    private TextArea txtConsole;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        instance=this;
        Platform.runLater(new Runnable(){

            @Override
            public void run() {
                
                
         titledPaneConsole.setExpanded(false);
         
         titledPaneConsole.expandedProperty().addListener(new ChangeListener<Boolean>(){

                    @Override
                    public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean newval) {
                        if(newval.equals(Boolean.TRUE))
                         {
                             anchorMain.setPrefHeight(474+txtConsole.getHeight());
                             anchorMain.setMaxHeight(474+txtConsole.getHeight());
                         }
                        else
                         {
                             anchorMain.setPrefHeight(474);
                             anchorMain.setMaxHeight(474);
                         }
                         anchorMain.autosize();

                    }
                });
                    Installer.startExecution();
                }
        });
    }    

    @FXML
    private void proceed(ActionEvent event) {
    }

    public void printToConsole(String line) {
        txtConsole.appendText(line+"\n");
    }

    public void setupComplete(int i) {
        switch(i)
        {
            case 1: progress1.setVisible(false);
                    imgDone1.setVisible(true);
                    break;
            case 2: progress2.setVisible(false);
                    imgDone2.setVisible(true);
                    break;
            case 3: progress3.setVisible(false);
                    imgDone3.setVisible(true);
                    break;   
            case 4: progress4.setVisible(false);
                    imgDone4.setVisible(true);
                    break;    
        }
    }
    
    
}
