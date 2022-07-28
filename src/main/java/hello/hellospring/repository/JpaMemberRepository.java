package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // JPA가 insert 쿼리 만들어서 다 집어넣고, member.setId까지 모든 걸 다해준다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);// find(조회할 타입, 식별자)
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) { // pk가 아닌 name으로 찾으려면 jpql 작성해야 된다.
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // jpql : 테이블 대상이 아닌, 객체 entity를 대상으로 쿼리를 날린다.
                .getResultList(); // Member Entity를 조회하고, (Member (as) m -> as가 생략됨) *이 아니라 m(객체) 자체를 조회함
    }
}
