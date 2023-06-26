package com.Hanium.Farm.Farm.Controller;

import com.Hanium.Farm.Farm.Domain.Fruit;
import com.Hanium.Farm.Farm.Service.FruitService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class FruitController {

    FruitService fruitService;
    Log log = LogFactory.getLog(FruitController.class);

    @Autowired
    public FruitController(FruitService fruitService){
        this.fruitService = fruitService;
    }

    @GetMapping("/search")
    @ResponseBody
    public String getFruitInfo(@RequestParam String fruit){ // 과일의 정보를 받아 client로 보내준다.
        Fruit info = fruitService.getFruitInfo(fruit);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String temp = gson.toJson(info);
        log.info(temp);
        return temp;
    }

    @GetMapping("/period")
    @ResponseBody
    public ArrayList<String> getPeriodFruits(@RequestParam int month){
        ArrayList<String> fruits =  fruitService.getPeriodFruits(month);

        return fruits;
    }
}
