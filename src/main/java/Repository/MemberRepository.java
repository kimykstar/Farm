package Repository;

import Domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class MemberRepository implements MemberRepositoryInterface {

    private final JdbcTemplate jdbcTemplate;

    // jdbc template 생성자로 생성
    public MemberRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Member> findMemberById(String id) {
        return null;
    }

    @Override
    public boolean join(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("farm").usingGeneratedKeyColumns("id");


        return false;
    }

    @Override
    public boolean update(Member member) {
        return false;
    }

    @Override
    public boolean delete(Member member) {
        return false;
    }

    @Override
    public String getPwHash(String id) {
        List<String> result = jdbcTemplate.query("SELECT pw FROM farm WHERE id = ?", pwRowMapper(), id);
        return result.get(0);
    }

    private RowMapper<String> pwRowMapper(){
        return (rs, rowNum) -> {
            String result = "None";
            result = rs.getString("pw");
            return result;
        };
    }
}
