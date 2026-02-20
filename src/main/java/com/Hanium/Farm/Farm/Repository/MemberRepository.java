package com.Hanium.Farm.Farm.Repository;

import com.Hanium.Farm.Farm.Vo.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MemberRepository implements MemberRepositoryInterface {

    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean join(Member member) {
        int result = jdbcTemplate.update(
                "INSERT INTO user VALUES (?, ?, ?, ?, ?);"
                , member.id(), member.pw(), member.name(), member.phone(), member.age());

        return result == 1;
    }

    @Override
    public boolean delete(String id) {
        int count = jdbcTemplate.update("DELETE FROM user WHERE id = ?", id);
        boolean result;
        if(count == 0){
            result = false;
        }else
            result = true;

        return result;
    }

    @Override
    public Member getMember(String id) {
        List<String> result = jdbcTemplate.query("SELECT * FROM user WHERE id = ?", pwRowMapper(), id);
        String memberInfo = result.get(0);
        String[] info = memberInfo.split(" ");

        return new Member(info[0], info[1], info[2], info[3], Integer.parseInt(info[4]));
    }

    private RowMapper<String> pwRowMapper(){
        return (rs, rowNum) -> {
            StringBuilder temp = new StringBuilder(rs.getString("id"));
            temp.append(" " + rs.getString("pw"));
            temp.append(" " + rs.getString("name"));
            temp.append(" " + rs.getString("phone_num"));
            temp.append(" " + rs.getString("age"));

            return temp.toString();
        };
    }
}
