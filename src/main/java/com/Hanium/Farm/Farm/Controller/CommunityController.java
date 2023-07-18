package com.Hanium.Farm.Farm.Controller;

import com.Hanium.Farm.Farm.Service.CommunityService;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RestController
public class CommunityController {
    CommunityService communityService;

    Log log = LogFactory.getLog(CommunityService.class);
    @Autowired
    public CommunityController(CommunityService communityService){
        this.communityService = communityService;
    }

    @PostMapping("/regist")
    public String registReview(HttpServletRequest request) throws IOException {
        String result = "False";
        System.out.println("regist");
        try{
            InputStream inputStream = request.getInputStream();
            String message = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            if(message != null)
                result = "true";
            System.out.println(message);
        }catch(IOException e){
            log.info("register Error");
        }
        return result;
    }

    @PostMapping("image")
    public String registImage(HttpServletRequest request) throws IOException {
        ServletInputStream inputStream = request.getInputStream();

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        byte[] imageData = request.getInputStream().readAllBytes();

        if(imageData != null) {
            try{
                FileOutputStream fos = new FileOutputStream("../../resources/ReviewImage");
                fos.write(imageData);
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            System.out.println("False");
        }
        return "true";
    }

}
