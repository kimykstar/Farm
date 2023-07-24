package com.Hanium.Farm.Farm.Controller;

import com.Hanium.Farm.Farm.Domain.ReviewInfo;
import com.Hanium.Farm.Farm.Service.CommunityService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

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
        communityService.registReview(image, review);

        return "true";
    }

    @GetMapping("reviews")
    public ArrayList<ReviewInfo> getFruitReviews(@RequestParam("fruit_name") String fruit_name){
        ArrayList<ReviewInfo> infos = communityService.getReviews(fruit_name);

        return infos;
    }

}
