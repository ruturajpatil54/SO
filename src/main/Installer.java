/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

//import backgroundtasks.TomcatInstaller;
import backgroundtasks.Task1;
import backgroundtasks.Task2;
import backgroundtasks.Task3;
import backgroundtasks.Task4;
import controller.ProgressController;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Admin
 */
public class Installer extends Application {
   
    public static ExecutorService pool=Executors.newSingleThreadExecutor(); // executes single thread at a time
    public static void startExecution() 
    {
       Service<Void> backgroundService=new Service<Void>(){

           @Override
           protected Task<Void> createTask() {
               
               
               return new Task<Void>(){

                   @Override
                   protected Void call() throws Exception {
                                pool.submit(new Task1());
                        pool.submit(new Task2());

                        pool.submit(new Task3());
                        pool.submit(new Task4());
                        return null;
                   }
               };
           }
           
       };
       backgroundService.start();
    }
    @Override
    public void start(final Stage stage) throws Exception 
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/presentation/"));
         AnchorPane root = (AnchorPane)loader.load(getClass().getResource("/presentation/Progress.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setResizable(true);
        Platform.setImplicitExit(true);
        stage.show();
       
         
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
   

}
