package com.Hanium.Farm.Farm.Domain;

public class Nutrition {

    private String nutrition;
    private String unit;
    private double amount;

    public Nutrition(String nutrition, String unit, double amount){
        this.nutrition = nutrition;
        this.unit = unit;
        this.amount = amount;
    }

    public String getNutrition() {
        return nutrition;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


}
