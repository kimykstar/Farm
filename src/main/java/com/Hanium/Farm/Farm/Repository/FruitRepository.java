package com.Hanium.Farm.Farm.Repository;

import com.Hanium.Farm.Farm.Domain.Fruit;
import com.Hanium.Farm.Farm.Domain.FruitInfo;
import com.Hanium.Farm.Farm.Domain.Nutrition;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class FruitRepository implements FruitRepositoryInterface{

    private final JdbcTemplate jdbcTemplate;
    Log log = LogFactory.getLog(FruitRepository.class);
    @Autowired
    public FruitRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Fruit getFruitInfo(String fruit) {
        List<Fruit> infos = jdbcTemplate.query("SELECT * FROM fruits WHERE fruit_name=?;", pwRowMapper(getNutritionInfo(fruit)), fruit);

        Fruit info = infos.get(0);

        return info;
    }

    private RowMapper<Fruit> pwRowMapper(FruitInfo fruitInfo){
        return (rs, rowNum) -> {
            Fruit fruit = null;
            fruit = new Fruit(rs.getString("fruit_name"), rs.getString("calories"),
                    rs.getString("carbohydrate"), rs.getString("protein"),
                    rs.getString("fat"), rs.getString("sugar"), fruitInfo);

            return fruit;
        };
    }

    @Override
    public FruitInfo getNutritionInfo(String fruit) {
        List<Nutrition> i = jdbcTemplate.query("SELECT * FROM fn_table INNER JOIN effective ON fn_table.nutrition=effective.nutrition WHERE fruit_name=? and amount > 0 ORDER BY amount DESC;", nuRowMapper(), fruit);
        ArrayList<Nutrition> temp = new ArrayList<>(i);
        FruitInfo infos = new FruitInfo(fruit);
        infos.setInfoList(temp);
        return infos;
    }

    private RowMapper<Nutrition> nuRowMapper(){
        return (rs, rowNum) -> {
            String nutrition = rs.getString("nutrition");
            String unit = rs.getString("unit");
            double amount = rs.getDouble("amount");
            String type = rs.getString("n_type");
            String effect = rs.getString("effective");
            Nutrition info = new Nutrition(nutrition, unit, amount, type, effect);
            return info;
        };
    }
}
