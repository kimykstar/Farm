import Repository.MemberRepository;
import Repository.MemberRepositoryInterface;
import Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        // dataSource를 spring bean으로 등록
        this.dataSource = dataSource;
    }

    @Bean
    public MemberRepositoryInterface memberRepositoryInteface(){
        return new MemberRepository(dataSource);
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepositoryInteface());
    }


}
