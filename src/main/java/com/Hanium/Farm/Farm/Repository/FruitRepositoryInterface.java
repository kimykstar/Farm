package com.Hanium.Farm.Farm.Repository;

import com.Hanium.Farm.Farm.Domain.Fruit;
import com.Hanium.Farm.Farm.Domain.FruitInfo;

import java.util.Optional;

public interface FruitRepositoryInterface {

    Fruit getFruitInfo(String fruit);

    FruitInfo getNutritionInfo(String fruit);
}
