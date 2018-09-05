
package com.evosoft.mybackend;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * @author z003yhbx
 */
public class TimeSeriesHandler {
    ArrayList<Double> data;
    ArrayList<String> timestamp;

    public TimeSeriesHandler() {
        data = new ArrayList<>();
        timestamp = new ArrayList<>();

    }

    public int readJSONFile(String path, String name) {
        try {
            JSONParser parser = new JSONParser();
            Object obj;
            obj = parser.parse(new FileReader(path + name));
            JSONArray jsArr = (JSONArray) obj;

            DateTimeFormatter parser2 = ISODateTimeFormat.dateTimeParser();
            DateTimeFormatter parser1 = DateTimeFormat.mediumDateTime();

            for(int i=0; i<jsArr.size(); i++){
                JSONObject jsonObject = (JSONObject) jsArr.get(i);

                data.add(Double.parseDouble(jsonObject.get("Temperatur").toString()));
                timestamp.add(parser1.print(parser2.parseDateTime(jsonObject.get("_time").toString())));
            }

        } catch (Exception ex) {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, null, ex);
            return 1;
        }
        return 0;
    }
    
    public int writeJSONFile(String path, String name) {
        JSONObject obj = new JSONObject();
        obj.put("data", data);
        obj.put("timestamp", timestamp);
        
        try(FileWriter file = new FileWriter(path + name)){
            file.write(obj.toJSONString());
            file.flush();
        }catch(Exception ex){
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    

    public void printData() {
        //DateTimeFormatter parser1 = DateTimeFormat.mediumDateTime();
        for(int i = 0; i<data.size(); i++){
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, timestamp.get(i) + " " + data.get(i) + "\n");
            //System.out.print(timestamp.get(i) + " " + data.get(i) + "\n");
        }
    }
}
