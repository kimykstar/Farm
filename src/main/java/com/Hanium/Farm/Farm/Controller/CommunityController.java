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
        try{
            InputStream inputStream = request.getInputStream();
            String message = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            if(message != null)
                result = "true";
            System.out.println(message);
            System.out.println("-----------");
        }catch(IOException e){
            log.info("register Error");
        }
        return result;
    }

    @PostMapping("/image")
    public String registImage(HttpServletRequest request) throws IOException {
        System.out.println("image");
        ServletInputStream inputStream = request.getInputStream();

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println(messageBody);
//        byte[] imageData = request.getInputStream().readAllBytes();
//        System.out.println("image");
//        if(imageData != null) {
//            System.out.println("success");
//            System.out.println(imageData);
//        }else{
//            System.out.println("False");
//        }
        return "true";
    }

}
