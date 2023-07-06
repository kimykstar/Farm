package com.Hanium.Farm.Farm.Repository;

import com.Hanium.Farm.Farm.Domain.Fruit;
import com.Hanium.Farm.Farm.Domain.FruitInfo;
import com.Hanium.Farm.Farm.Domain.PeriodFruit;

import java.util.ArrayList;
import java.util.Optional;

public interface FruitRepositoryInterface {

    Fruit getFruitInfo(String fruit);
    FruitInfo getNutritionInfo(String fruit);
    ArrayList<PeriodFruit> getPeriodFruit(int month);

}
