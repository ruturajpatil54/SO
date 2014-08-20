/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import controller.ProgressController;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import main.Installer;

/**
 *
 * @author Admin
 */
public class ProcessExecutor extends Task<Integer>
{
    Logger logger =Logger.getLogger("ProcessExecutor");
    File dir;
    String []cmd;
    String cmds;
    int exitCode=-1;
    boolean NextStepExists=false;
    Task nextStep;
    public ProcessExecutor(String...cmd )
    {
        this.cmd=cmd;
        this.dir=new File(System.getProperty("user.dir"));
        this.nextStep=null;
        NextStepExists=false;
    }
    public ProcessExecutor(Task nextStep,String...cmd )
    {
        this.cmd=cmd;
        this.dir=new File(System.getProperty("user.dir"));
        this.nextStep=nextStep;
        NextStepExists=true;
    }
    public ProcessExecutor(Task nextStep,File dir,String...cmd)
    {
        this.cmd=cmd;
        this.dir=dir;
        this.nextStep=nextStep;
        NextStepExists=true;
    }
    

    @Override
    protected final Integer call()
    {
        cmds=new String();
            for(String i:cmd)
                cmds+=i+" "; // just to log cmd array
        
        try
        {
            
            logger.info("Starting new process with cmd > "+cmds);
            
            ProcessBuilder processBuilder=new ProcessBuilder(cmd);
            processBuilder.directory(dir);
            processBuilder.redirectErrorStream(true);
            Map<String, String> env = processBuilder.environment();
            // create custom environment 
            env.put("JAVA_HOME", "/opt/jdk1.7.0_45/"); 
            
            Process pr=processBuilder.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
               String line = in.readLine();
                while (line != null) {
                    logger.log(Level.FINE,line);
                    ProgressController.instance.printToConsole(line);
                    line = in.readLine();
                }
                BufferedReader er = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
               String erLine = in.readLine();
                while (erLine != null) {
                    logger.log(Level.FINE,erLine);
                    ProgressController.instance.printToConsole(erLine);
                    erLine = in.readLine();
                }
                
                
            exitCode=pr.waitFor();
            exitCode=pr.exitValue();
            logger.info("Exit Value="+exitCode);
            updateMessage("Completed Process");
            if(cmd[0].contains("ConfigService.bat"))
            {
                logger.info("PE succeeded()");
                if(NextStepExists)
                    
                Installer.pool.submit(nextStep);
                succeeded();
            }
            else if(exitCode!=0 && exitCode!=1)
            {
                logger.info("Failed to execute process commands >"+cmds+" with exit code="+exitCode);
                failed();
            }
            
            else
            {
                logger.info("PE succeeded()");
                if(NextStepExists)
                    Installer.pool.submit(nextStep);
                succeeded();
            }
            
        }
        catch(Exception e)
        {
            logger.log(Level.SEVERE,"Exception: Failed to execute process commands >"+cmds,e);
            updateMessage(e.getMessage());
        }
        
        return new Integer(exitCode);
        
        
    
    }
    @Override
    public void failed()
    {
        super.failed();
        logger.log(Level.SEVERE,"Failed to execute process commands >"+cmds+"; ExitCode="+exitCode);
        
    }

    
    
    
}
