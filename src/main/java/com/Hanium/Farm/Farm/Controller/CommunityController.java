package com.Hanium.Farm.Farm.Controller;

import com.Hanium.Farm.Farm.Domain.Review;
import com.Hanium.Farm.Farm.Domain.ReviewInfo;
import com.Hanium.Farm.Farm.Domain.ReviewPath;
import com.Hanium.Farm.Farm.Service.CommunityService;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@RestController
public class CommunityController {
    CommunityService communityService;

    Log log = LogFactory.getLog(CommunityService.class);
    @Autowired
    public CommunityController(CommunityService communityService){
        this.communityService = communityService;
    }

    @PostMapping("regist")
    public String registPost(@RequestParam("image") MultipartFile image, @RequestParam("review") String review) throws IOException {
        communityService.registReview(image, review, "upload", null);
        System.out.println(review);

        return "true";
    }

    @GetMapping("reviews")
    public ArrayList<ReviewInfo> getFruitReviews(@RequestParam("fruit_name") String fruit_name){
        ArrayList<ReviewInfo> infos = communityService.getReviews(fruit_name);

        return infos;
    }

    @GetMapping("userreviews")
    public ArrayList<ReviewInfo> getUserReviews(@RequestParam("user_id") String user_id){
        ArrayList<ReviewInfo> infos = communityService.getUserReviews(user_id);

        return infos;
    }

    @DeleteMapping("deletereview")
    public String deleteReview(@RequestParam("fruit_name") String fruit_name, @RequestParam("user_id") String user_id, @RequestParam("reviewtime") String reviewTime){
        Review review = new Review(fruit_name,  reviewTime, user_id, "", "", 0);
        String result = communityService.deleteReview(review);


        return result;
    }

    // 이미지와 본문 모두 수정한 경우
    @PutMapping("updateimage")
    public String updateReview(@RequestParam("image") MultipartFile image, @RequestParam("review") String review, @RequestParam String fileName) throws IOException {
        String flag = "false";
        flag = communityService.deleteImage(fileName);
        flag = communityService.registReview(image, review, "update", fileName);

        System.out.println(review);
        return "true";
    }

    // 본문만 수정한 경우
    // 파일명도 바뀌도록 해야함..
    @PutMapping("updatebody")
    public String updateBodyReview(@RequestBody ReviewPath reviewPath){
        String flag = "false";
        Review review = reviewPath.getReview();
        String fileName = reviewPath.getFilePath();
//        Review review = reviewInfo.keySet().iterator().next();
//        String fileName = reviewInfo.get(review);
        flag = communityService.updateReview(review, fileName);

        return flag;
    }
}
