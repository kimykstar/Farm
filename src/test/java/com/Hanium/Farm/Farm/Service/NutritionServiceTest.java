package com.Hanium.Farm.Farm.Service;

import com.Hanium.Farm.Farm.Domain.FruitInfo;
import com.Hanium.Farm.Farm.Domain.Nutrition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;

@SpringBootTest
public class NutritionServiceTest {

    @Autowired
    NutritionService nutritionService;

    @Test
    public void 영양소받기(){
        String name = "참외";
        FruitInfo infos = nutritionService.getAllInfos(name);
        Iterator iter = infos.iterator();
        while(iter.hasNext()){
            Nutrition n = (Nutrition)iter.next();
            System.out.println(n.getNutrition() + ", " + n.getAmount());
        }
    }
}
