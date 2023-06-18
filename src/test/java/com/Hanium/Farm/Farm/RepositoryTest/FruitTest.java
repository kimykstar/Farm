package com.Hanium.Farm.Farm.RepositoryTest;

import com.Hanium.Farm.Farm.Domain.FruitInfo;
import com.Hanium.Farm.Farm.Domain.Nutrition;
import com.Hanium.Farm.Farm.Repository.NutritionInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;

@SpringBootTest
@Transactional
public class FruitTest {
    @Autowired
    NutritionInterface nutritionInteface;
    @Test
    public void 과일정보가져오기(){
        String name = "참외";
        FruitInfo infos;
        infos = nutritionInteface.getNutritionInfos(name);
        ArrayList<Nutrition> li = infos.getAllInfos();
        Iterator<Nutrition> it = li.iterator();
        while(it.hasNext()){
            Nutrition temp = it.next();
            System.out.println(temp.getNutrition() + ", " + temp.getAmount());
        }
    }
}
