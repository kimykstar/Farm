package com.Hanium.Farm.Farm.Repository;

import com.Hanium.Farm.Farm.Domain.Fruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class FruitRepository implements FruitRepositoryInterface{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FruitRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Fruit getFruitInfo(String fruit) {
        List<Fruit> infos = jdbcTemplate.query("SELECT * FROM fruits WHERE fruit_name=?;", pwRowMapper(), fruit);

        Fruit info = infos.get(0);

        return info;
    }

    private RowMapper<Fruit> pwRowMapper(){
        return (rs, rowNum) -> {
            Fruit fruit = null;
            fruit = new Fruit(rs.getString("fruit_name"), rs.getString("calories"),
                    rs.getString("carbohydrate"), rs.getString("protein"),
                    rs.getString("fat"), rs.getString("sugar"));

            return fruit;
        };
    }
}
