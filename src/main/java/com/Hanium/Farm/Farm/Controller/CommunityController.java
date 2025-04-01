package com.Hanium.Farm.Farm.Controller;

import com.Hanium.Farm.Farm.Domain.Review;
import com.Hanium.Farm.Farm.Domain.ReviewInfo;
import com.Hanium.Farm.Farm.Domain.ReviewPath;
import com.Hanium.Farm.Farm.Domain.SingleComment;
import com.Hanium.Farm.Farm.Service.CommunityService;
import com.google.gson.Gson;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@RestController
public class CommunityController {
    CommunityService communityService;

    @Autowired
    public CommunityController(CommunityService communityService){
        this.communityService = communityService;
    }

    @PostMapping("regist")
    public String registPost(@RequestParam("image") MultipartFile image, @RequestParam("review") String review) throws IOException {
        communityService.registReview(image, review, "upload", null);

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
        Review review = new Review(fruit_name,  reviewTime, user_id, "", "", "", "");
        String result = communityService.deleteReview(review);


        return result;
    }

    // 이미지와 본문 모두 수정한 경우
    @PutMapping("updateimage")
    public String updateReview(@RequestParam("image") MultipartFile image, @RequestParam("review") String review, @RequestParam String fileName) {
        communityService.deleteImage(fileName);
        communityService.registReview(image, review, "update", fileName);

        return "true";
    }

    @PutMapping("updatebody")
    public String updateBodyReview(@RequestBody ReviewPath reviewPath){
        Review review = reviewPath.getReview();
        String fileName = reviewPath.getFilePath();

        return communityService.updateReview(review, fileName);
    }

    @GetMapping("comments")
    public ArrayList<SingleComment> getOneComment(@RequestParam String review_id){
        ArrayList<SingleComment> comments = communityService.getComments(review_id);

        return comments;
    }

    @PostMapping("insertComment")
    public boolean insertComment(HttpServletRequest request) throws IOException{
        ServletInputStream inputStream = request.getInputStream();

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        SingleComment comment = new Gson().fromJson(messageBody, SingleComment.class);

        return communityService.insertComment(comment);
    }

    @DeleteMapping("removeComment")
    public boolean removeComment(@RequestParam("review_id") String review_id, @RequestParam("user_id") String user_id, @RequestParam("comment") String comment){
        SingleComment com = new SingleComment(user_id, comment, "", review_id);

        return communityService.removeComment(com);
    }

    @PostMapping("insertGood")
    public boolean insertGood(HttpServletRequest request) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        String[] messages = messageBody.split(" ");
        String review_id = messages[0];
        String user_id = messages[1];

        return communityService.insertGood(review_id, user_id);
    }

    @DeleteMapping("deleteGood")
    public boolean deleteGood(@RequestParam("review_id") String review_id, @RequestParam("user_id") String user_id){
        return communityService.deleteGood(review_id, user_id);
    }

}
