/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evosoft.mybackend.RESTApi;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/**
 *
 * @author z003yhbx
 */

@RestController
public class RESTController {
    
    @RequestMapping(
        value = "/json",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public String getFormattedjson()
        throws IOException{
        String path = "src"+ File.separator + "main" + File.separator + "java" 
                + File.separator + "com" + File.separator + "evosoft"+ File.separator + "mybackend"
                + File.separator + "timeseriesFormatted.json";
        
        byte[] encoded = Files.readAllBytes(Paths.get(path));        
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
