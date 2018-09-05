package com.evosoft.mybackend;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.evosoft.mybackend.hello.StartREST;

/**
 *
 * @author z003yhbx
 */
public class Start {

    public static void main(String[] args) {
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, "Starting main.");
        
        String localDir = System.getProperty("user.dir");
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, "localDir: {0}", localDir);
        String path = "src"+ File.separator + "main" + File.separator + "java" 
                + File.separator + "com" + File.separator + "evosoft"+ File.separator + "mybackend"
                + File.separator;
        TimeSeriesHandler TSHandler= new TimeSeriesHandler();
        TSHandler.readJSONFile(path, "timeseriesTemp.json");
        TSHandler.writeJSONFile(path, "timeseriesFormatted.json");
        TSHandler.printData();
        
        StartREST.start();
        /*try {
            StartServer.start();
        } catch (IOException ex) {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, null, ex);
        }*/
    }

}
