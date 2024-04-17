package hello.domain;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

// (JUnit 5~) test 코드에서 public 생략 가능
class MemberRepositoryTest {
    // 테스트 할 대상을 가지고 온다.
    // MemberRepository => 싱글톤 패턴으로 생성, new 키워드를 통한 객체 생성이 불가하다.
    // 사전에 만들어 둔 getInstance() 메서드를 사용하여 생성된 객체를 불러온다.
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach  // 하나의 테스트가 종료되면 실행된다.
    // 테스트 실행 순서는 보장되지 않으므로, 테스트가 종료될 때마다 clearStore를 진행한다.
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {   // 회원 정보 저장에 대한 Test
        // given: ~가 주어지고
        Member member = new Member("soo", 20);  // Member 객체 생성

        // when: ~ 했을 때
        Member saveMember = memberRepository.save(member);  // 회원 정보 저장 후, 반환된 객체를 저장

        // then: 결과는 ~ 이다.
        Member findMember =  memberRepository.findById(saveMember.getId()); // ID를 통해 찾은 회원 정보를 저장

        // org.assertj.core ~ import 선택 주의!
        // 저장한 회원 정보와 ID를 통해 찾은 회원 정보는 동일해야 한다.
        Assertions.assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    void findAll() {  // 전체 회원 정보 조회에 대한 Test
        // given
        // Member 객체 생성: member1, member2
        Member member1 = new Member("jin", 21);
        Member member2 = new Member("shin", 22);

        // 생성한 회원 정보를 저장한다.
        memberRepository.save(member1);
        memberRepository.save(member2);

        // when
        // 저장소의 모든 회원 정보를 꺼내서 List에 저장한다.
        List<Member> result = memberRepository.findAll();

        // then
        // 모든 회원 정보를 꺼낸 것인지 확인한다.
        Assertions.assertThat(result.size()).isEqualTo(2);

        // List 내에 member1, member2 객체가 있는지 확인한다.
        Assertions.assertThat(result).contains(member1, member2);
    }
}
