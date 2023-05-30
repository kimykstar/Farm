package com.Hanium.Farm.Farm.Controller;

import com.Hanium.Farm.Farm.Domain.Fruit;
import com.Hanium.Farm.Farm.Service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FruitController {

    FruitService fruitService;

    @Autowired
    public FruitController(FruitService fruitService){
        this.fruitService = fruitService;
    }

    @GetMapping("/search")
    @ResponseBody
    public Fruit getFruitInfo(@RequestParam String fruit){ // 과일의 정보를 받아 client로 보내준다.
        Fruit info = fruitService.getFruitInfo(fruit);

        return info;
    }
}
