package com.Hanium.Farm.Farm.Service;

import com.Hanium.Farm.Farm.Domain.Review;
import com.Hanium.Farm.Farm.Domain.ReviewInfo;
import com.Hanium.Farm.Farm.Repository.CommunityRepositoryInterface;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CommunityService {
    CommunityRepositoryInterface communityRepository;

    @Autowired
    public CommunityService(CommunityRepositoryInterface communityRepository){
        this.communityRepository = communityRepository;
    }

    public void registReview(MultipartFile image, String review){
        String filePath = "src/main/resources/static/";

        try{
            if(!image.isEmpty()) { // Image처리 Image가 존재할 경우 Image를 경로에 저장한다.
                byte[] imageData = image.getBytes();
                byte[] decodedBytes = Base64.getDecoder().decode(image.getOriginalFilename()); // Base65로부터 디코딩 함
                String originalImageName = new String(decodedBytes, StandardCharsets.UTF_8); // UTF-8로부터 디코딩함
                String totalPath = filePath + originalImageName; // Image파일 이름은 과일이름_사용자ID_리뷰시간.jpg
                FileOutputStream fos = new FileOutputStream(totalPath); // Image이름을 정하여 지정된 경로에 이미지 파일을 저장한다.
                System.out.println("File Name : " + originalImageName);
                fos.write(imageData);
            }
            Gson gson = new Gson();
            Review review_content = gson.fromJson(review, Review.class); // json으로부터 Review객체 생성
            System.out.println("이미지 업로드 성공");
            communityRepository.upload_review(review_content);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    // ArrayList형태로 Review정보와 Image데이터를 전달한다.
    public ArrayList<ReviewInfo> getReviews(String fruit_name) { // Review정보와 image Byte데이터를 Base64로 String형식으로 만들어 반환
        ArrayList<Review> reviews = communityRepository.getReviewInfo(fruit_name);
        String filePath = "src/main/resources/static/";
        ArrayList<ReviewInfo> result = new ArrayList<ReviewInfo>();

        Iterator<Review> it = reviews.iterator();
        while(it.hasNext()){ // 리뷰 정보에 담긴 fruit_name, user_id, review_time을 합하여 이미지 이름 생성
            Review review = it.next();
            String[] temp = review.getReview_time().split(" ");
            String review_time = temp[0] + "." + temp[1].replace(":", "-");

            String fileName = review.getFruit_name() + "_" + review.getUser_id() + "_" + review_time + ".jpg";
            try {
                byte[] imageBytes = Files.readAllBytes(Paths.get(filePath + fileName));
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                result.add(new ReviewInfo(review, base64Image)); // fileName을 image로
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        return result;
    }
}
