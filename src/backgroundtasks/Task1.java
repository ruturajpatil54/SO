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

/**
 *
 * @author ruturaj
 */
public class Task1 extends Task<Integer>
{

    @Override
    protected Integer call() throws Exception {
        int exitCode=-1;
        try
        {
            ProgressController.instance.printToConsole("Starting Task1 ...");
            Thread.sleep(1000);// replace with actual code
            ProgressController.instance.printToConsole("Completed Task1");
            exitCode=0;
            setOnSucceeded(new EventHandler(){

                @Override
                public void handle(Event t) {
                    ProgressController.instance.setupComplete(1);
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
