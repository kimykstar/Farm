package com.Hanium.Farm.Farm.Repository;

import com.Hanium.Farm.Farm.Domain.FruitInfo;
import com.Hanium.Farm.Farm.Domain.Nutrition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NutritionRepository implements NutritionInterface{
    private final JdbcTemplate jdbcTemplate;
    public NutritionRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    Logger log = LoggerFactory.getLogger(NutritionRepository.class);
    @Override
    public FruitInfo getNutritionInfos(String fruit) { // fruit의 이름에 맞는 영양 정보의 배열 클래스를 반환한다.
        FruitInfo fruitInfos = new FruitInfo(fruit);

        List<Nutrition> i = jdbcTemplate.query("SELECT * FROM FN_TABLE WHERE fruit_name=?;", infoRowMapper(), fruit);
        log.info(fruit);
        ArrayList<Nutrition> info = new ArrayList<Nutrition>(i);
        fruitInfos.setInfoList(info);

        Iterator<Nutrition> it = fruitInfos.iterator();

        return fruitInfos;
    }

    // RowMapper사용법 알기 rowNum변수를 이용해야하나?
    private RowMapper<Nutrition> infoRowMapper(){ // RowMapper를 반환하는 메소드
        return (rs, rowNum) -> {
            String nutrition = rs.getString("nutrition");
            String unit = rs.getString("unit");
            double amount = rs.getDouble("amount");
            Nutrition info = new Nutrition(nutrition, unit, amount);
            return info;
        };
    }
}
