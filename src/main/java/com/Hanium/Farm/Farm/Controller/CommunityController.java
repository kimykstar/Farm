package com.Hanium.Farm.Farm.Controller;

import com.Hanium.Farm.Farm.Domain.Review;
import com.Hanium.Farm.Farm.Service.CommunityService;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
public class CommunityController {
    CommunityService communityService;

    Log log = LogFactory.getLog(CommunityService.class);
    @Autowired
    public CommunityController(CommunityService communityService){
        this.communityService = communityService;
    }

    @PostMapping("regist")
    public String registPost(@RequestParam("image")MultipartFile image, @RequestParam("review") String review) throws IOException {
        if(!image.isEmpty()) { // 이미지를 등록했다면
            String filePath = "src/main/resources/static/";
            byte[] imageData = image.getBytes();
            if(imageData != null) {
                try{
                    byte[] decodedBytes = Base64.getDecoder().decode(image.getOriginalFilename()); // Base65로부터 디코딩 함
                    String originalImageName = new String(decodedBytes, StandardCharsets.UTF_8); // UTF-8로부터 디코딩함

                    FileOutputStream fos = new FileOutputStream(filePath + originalImageName);
                    System.out.println("File Name : " + originalImageName);
                    fos.write(imageData);
                    Gson gson = new Gson();
                    Review review_content = gson.fromJson(review, Review.class); // json으로부터 Review객체 생성
                    System.out.println("이미지 업로드 성공");
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else{
                System.out.println("False");
            }
        }
        else
            System.out.println("image is Null");

        if(review != null && !review.isEmpty()){
            System.out.println(review);
            System.out.println("Review Info getted Successly");
        }

        return "true";
    }

}
