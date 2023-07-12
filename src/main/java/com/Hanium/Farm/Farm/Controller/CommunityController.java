package com.Hanium.Farm.Farm.Controller;

import com.Hanium.Farm.Farm.Service.CommunityService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CommunityController {
    CommunityService communityService;

    @Autowired
    public CommunityController(CommunityService communityService){
        this.communityService = communityService;
    }
    @PostMapping("/load")
    public void handleImageUpload(MultipartFile imageFile){
        if (imageFile.isEmpty()) {
            System.out.println("Image Load Failed!!");
        }else{
            communityService.image_pro();
        }
    }

}
