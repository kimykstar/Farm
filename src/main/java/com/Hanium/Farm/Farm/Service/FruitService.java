package com.Hanium.Farm.Farm.Service;

import com.Hanium.Farm.Farm.Domain.Fruit;
import com.Hanium.Farm.Farm.Repository.FruitRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;

public class FruitService {

    FruitRepositoryInterface fruitRepository;

    @Autowired
    public FruitService(FruitRepositoryInterface fruitRepository){
        this.fruitRepository = fruitRepository;
    }

    public Fruit getFruitInfo(String fruit){
        return fruitRepository.getFruitInfo(fruit);
    }

}
