package com.Hanium.Farm.Farm.Service;

import com.Hanium.Farm.Farm.Domain.FruitInfo;
import com.Hanium.Farm.Farm.Domain.Nutrition;
import com.Hanium.Farm.Farm.Repository.NutritionInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;

public class NutritionService {
    NutritionInterface nutritionInterface;
    Logger log = LoggerFactory.getLogger(NutritionService.class);

    @Autowired
    public NutritionService(NutritionInterface nutritionInterface){
        this.nutritionInterface = nutritionInterface;
    }

    public FruitInfo getAllInfos(String f_name){
        FruitInfo infos = nutritionInterface.getNutritionInfos(f_name);
        Iterator<Nutrition> it = infos.iterator();
        while(it.hasNext()){
            Nutrition n = it.next();
            log.info(n.getNutrition());
        }
        return infos;
    }
}
