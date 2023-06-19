package com.Hanium.Farm.Farm.Controller;

import com.Hanium.Farm.Farm.Domain.FruitInfo;
import com.Hanium.Farm.Farm.Service.NutritionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NutritionController {

    NutritionService nutritionService;

    @Autowired
    public NutritionController(NutritionService nutritionService){
        this.nutritionService = nutritionService;
    }

    Logger log = LoggerFactory.getLogger(NutritionController.class);
    @GetMapping(value = "/info")
    public FruitInfo getFruitInfo(@RequestParam String name){
        FruitInfo infos = nutritionService.getAllInfos(name);

        return infos;
    }
}
