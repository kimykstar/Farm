package com.Hanium.Farm.Farm.Repository;

import com.Hanium.Farm.Farm.Domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class CommunityRepository implements CommunityRepositoryInterface{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CommunityRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Review게시글 등록
    @Override
    public void upload_review(Review review) {
        String fruit_name = review.getFruit_name();
        String review_time = review.getReview_time();
        String user_id = review.getUser_id();
        String content = review.getContent();
        String flavor = review.getFlavor();
        int good = 0;

        if(flavor != null){
            jdbcTemplate.update("INSERT INTO review(fruit_name, review_time, id, content, flavor, good)" +
                    "VALUES (?, ?, ?, ?, ?, ?);", fruit_name, review_time, user_id, content, flavor, good);
        }else{
            jdbcTemplate.update("INSERT INTO review(fruit_name, review_time, id, content, good)" +
                    "VALUES (?, ?, ?, ?, ?, ?);", fruit_name, review_time, user_id, content, good);
        }
    }

    // 과일 이름 별 전체 게시물 받아오기
    @Override
    public ArrayList<Review> getReviewInfo(String fruit_name){;
        List<Review> result = jdbcTemplate.query("SELECT * FROM review WHERE fruit_name=?", reviewMapper(), fruit_name);
        ArrayList<Review> reviews = new ArrayList<>();
        reviews.addAll(result);
        return reviews;
    }

    private RowMapper<Review> reviewMapper(){
        return (rs, rowNum) ->{
            String fruit_name = rs.getString("fruit_name");
            String review_time = rs.getString("review_time");
            String user_id = rs.getString("id");
            String content = rs.getString("content");
            String flavor = rs.getString("flavor");
            int good = rs.getInt("good");
            if(content != null) // 내용이 있는 경우
                return new Review(fruit_name, review_time, user_id, content, flavor, good);
            else // 내용이 없는 경우
                return new Review(fruit_name, review_time, user_id, null, flavor, good);
        };
    }
}
