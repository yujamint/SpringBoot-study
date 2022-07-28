package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { // 인터페이스 다중 상속
    // JpaRepository<타입, 식별자(PK)>
    // Spring Data Jpa가 Jpa를 상속받고 있으면, 스프링이 구현체를 자동으로 만들어서 Bean에 등록해준다.
    // SpringConfig에서 MemberRepository 인젝션 받으면 SpringDataJpa로 만든 구현체가 자동으로 등록이 된다.

    //JPQL select m from member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
