package com.Hanium.Farm.Farm.Domain;

public class Fruit {
    private String fruit_name;
    private String calories;
    private String carbohydrate;
    private String protein;
    private String fat;
    private String sugar;
    private FruitInfo fruitInfo;


    // constructor ----------------------------

    public Fruit(){}

    public FruitInfo getFruitInfo() {
        return fruitInfo;
    }

    public Fruit(String fruit_name, String calories, String carbohydrate, String protein, String fat, String sugar){
        this.fruit_name = fruit_name;
        this.calories = calories;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.fat = fat;
        this.sugar = sugar;
    }

    public Fruit(String fruit_name, String calories, String carbohydrate, String protein, String fat, String sugar, FruitInfo fruitInfo){
        this.fruit_name = fruit_name;
        this.calories = calories;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.fat = fat;
        this.sugar = sugar;
        this.fruitInfo = fruitInfo;
    }

    // getter and setter-------------------

    public void setFruit_name(String fruit_name) {
        this.fruit_name = fruit_name;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public void setCarbohydrate(String carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getFruit_name() {
        return fruit_name;
    }

    public String getCalories() {
        return calories;
    }

    public String getCarbohydrate() {
        return carbohydrate;
    }

    public String getProtein() {
        return protein;
    }

    public String getFat() {
        return fat;
    }

    public String getSugar() {
        return sugar;
    }

    public void setFruitInfo(FruitInfo fruitInfo) {
        this.fruitInfo = fruitInfo;
    }

}
