package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig { // 자바 코드로 직접 스프링 빈 등록하기

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) { // 스프링 컨테이너에서 memberRepository 타입으로 받을 수 있는 빈을 찾는다.
        // JpaRepository를 상속받는 SpringDataJpa 인터페이스를 만들어놨기 때문에 스프링이 알아서 구현체를 만들고, 스프링 빈에 등록해놓는다.
        // 그렇기 때문에 인젝션 받을 수 있다.
        this.memberRepository = memberRepository;
    }


    @Bean // 내가 빈을 등록할 거야~
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

}

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
//}
