/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package backgroundtasks;

import controller.ProgressController;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import utils.ProcessExecutor;

/**
 *
 * @author ruturaj
 */
public class Task2 extends Task<Integer>
{

    @Override
    protected Integer call() throws Exception {
        int exitCode=-1;
        try
        {
            ProgressController.instance.printToConsole("Starting Task2 ...");
            ProcessExecutor pe=new ProcessExecutor("echo","Executing task 2");
            pe.run();
            
            ProgressController.instance.printToConsole("Completed Tas2");
            exitCode=0;
            setOnSucceeded(new EventHandler(){

                @Override
                public void handle(Event t) {
                    ProgressController.instance.setupComplete(2);
                }
            });
        }
        catch(Exception e)
        {
            e.printStackTrace();
            exitCode=-2;
        }
        return exitCode;
    }
    
    
}
