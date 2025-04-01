package com.Hanium.Farm.Farm.Repository;

import com.Hanium.Farm.Farm.Domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class FruitRepository implements FruitRepositoryInterface{

    private final JdbcTemplate jdbcTemplate;
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
        return (rs, rowNum) ->
            new Fruit(rs.getString("fruit_name"), rs.getString("file_name"), rs.getString("calories"),
                    rs.getString("carbohydrate"), rs.getString("protein"),
                    rs.getString("fat"), rs.getString("sugar"), fruitInfo);
    }

    @Override
    public FruitInfo getNutritionInfo(String fruit) {
        List<Nutrition> i = jdbcTemplate.query("SELECT * FROM fn_table INNER JOIN effective ON fn_table.nutrition=effective.nutrition WHERE fruit_name=? and amount > 0;", nuRowMapper(), fruit);
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

    // 제철 기간에 맞는 과일들의 List를 반환한다.
    @Override
    public ArrayList<PeriodFruit> getPeriodFruit(int month) {
        List<PeriodFruit> fruits = jdbcTemplate.query("SELECT period.fruit_name, fruits.file_name, period.start, period.end FROM period INNER JOIN fruits ON fruits.fruit_name=period.fruit_name WHERE start <= ? and end >= ?;", peRowMapper(), month, month);
        ArrayList<PeriodFruit> result = new ArrayList<>(fruits);

        return result;
    }

    private RowMapper<PeriodFruit> peRowMapper(){
        return (rs, rowNum) -> new PeriodFruit(rs.getString("fruit_name"), rs.getString("file_name"), rs.getInt("start"), rs.getInt("end"));
    }

    @Override
    public ArrayList<RecommendFruit> getRecommendFruit(String[] nutritions) {
        ArrayList<RecommendFruit> result = new ArrayList<>();
        for(String s : nutritions){
            List<RecommendFruit> fruits =
                    jdbcTemplate.query("SELECT fruits.fruit_name, file_name, nutrition, amount, unit FROM fruits LEFT JOIN fn_table " +
                            "ON fruits.fruit_name=fn_table.fruit_name " +
                            "WHERE nutrition=? and amount > 0 ORDER BY amount DESC", recommendMapper(), s);

            result.addAll(fruits);
        }
        return result;
    }

    private RowMapper<RecommendFruit> recommendMapper(){
        return (rs, rowNum) ->{
            System.out.println(rs.getString("amount"));
            return new RecommendFruit(rs.getString("file_name"), rs.getString("fruit_name"), rs.getString("nutrition"), rs.getString("amount"), rs.getString("unit"));
        };
    }

    @Override
    public ArrayList<String> getFruitNames(){
        ArrayList<String> names = new ArrayList<>();

        List<String> list = jdbcTemplate.query("SELECT fruit_name FROM fruits", fruitnameMapper());
        names.addAll(list);

        return names;
    }

    private RowMapper<String> fruitnameMapper(){
        return (rs, rowNum) -> rs.getString("fruit_name");
    }

    @Override
    public ArrayList<String> getHotFruit(){
        ArrayList<String> list = new ArrayList<>();

        List<String> names = jdbcTemplate.query("SELECT * FROM review LEFT JOIN (SELECT review.review_id, count(*) as cnt FROM review LEFT JOIN good ON review.review_id=good.review_id ORDER BY cnt DESC)" +
                "as t1 ON review.review_id=t1.review_id", hotFruitMapper(), null);
        Set<String> sets = new LinkedHashSet<>();
        Iterator<String> it = names.iterator();

        while(it.hasNext() && sets.size() <= 3)
            sets.add(it.next());

        list.addAll(sets);

        return list;
    }

    private RowMapper<String> hotFruitMapper(){
        return (rs, rowNum)-> rs.getString("fruit_name");
    }
}
