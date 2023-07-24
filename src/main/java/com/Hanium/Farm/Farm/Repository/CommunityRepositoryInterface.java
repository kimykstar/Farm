package com.Hanium.Farm.Farm.Repository;

import com.Hanium.Farm.Farm.Domain.Review;

import java.util.ArrayList;

public interface CommunityRepositoryInterface {

    // 게시물 업로드
    public void upload_review(Review review);

    public ArrayList<Review> getReviewInfo(String fruit_name);
}
