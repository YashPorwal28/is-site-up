package com.javalearn.isthesiteup.Controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:5173/")
public class UrlCheckController {
    
    private final String SITE_IS_UP = "Site is up!";
    private final String SITE_IS_DOWN = "Site is down!";
    private final String INCORRECT_URL = "URL is incorrect";
    

    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url){
        String returnMessage = "";
        try {
            URL urlObj = new URL(url);
            HttpsURLConnection conn = (HttpsURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCodeCategory = conn.getResponseCode() / 100;
            if(responseCodeCategory != 2 && responseCodeCategory != 3){
                    returnMessage = SITE_IS_DOWN;
            }else{
                returnMessage = SITE_IS_UP;
            }
        } catch (MalformedURLException e) {
            
            return returnMessage = INCORRECT_URL;
        } catch (IOException e) {
                return returnMessage = SITE_IS_DOWN;
        }


        return returnMessage;

    }



}
