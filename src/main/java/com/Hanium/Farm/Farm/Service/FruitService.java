package com.Hanium.Farm.Farm.Service;

import com.Hanium.Farm.Farm.Domain.Fruit;
import com.Hanium.Farm.Farm.Repository.FruitRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.logging.Logger;

public class FruitService {

    FruitRepositoryInterface fruitRepository;

    @Autowired
    public FruitService(FruitRepositoryInterface fruitRepository){
        this.fruitRepository = fruitRepository;
    }

    public Fruit getFruitInfo(String fruit){
        Fruit info = fruitRepository.getFruitInfo(fruit);

        return info;
    }

    public ArrayList<String> getPeriodFruits(int month){
        ArrayList<String> fruits = fruitRepository.getPeriodFruit(month);
        return fruits;
    }

}
