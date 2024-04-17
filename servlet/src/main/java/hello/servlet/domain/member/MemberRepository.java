package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 현재는 동시성 문제가 고려돼 있지 않은 상태이다.
 * 실무에서는 ConcurrentHashMap, AtomicLong 사용을 고려해야 한다.
 */

public class MemberRepository {
    // Key: ID, Value: Member 객체
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;  // ID 증가 체크

    // 싱글톤 패턴 사용
    private static final MemberRepository instance = new MemberRepository();

    // getInstance()를 사용해야만 인스턴스 조회가 가능하다.
    public static MemberRepository getInstance() {
        return instance;
    }

    // 싱글톤 패턴을 사용 => 생성자의 접근 제한 private 설정, 객체 생성을 막는다.
    private MemberRepository() {

    }

    // 회원 정보 저장
    public Member save(Member member) {
        // ID를 1만큼 증가시킨다.
        member.setId(++sequence);
        store.put(member.getId(), member);

        return member;
    }

    // 특정 회원 정보 조회
    public Member findById(Long id) {
        // 매개변수로 받은 ID를 사용하여 Map에서 회원 정보를 꺼낸다.
        return store.get(id);
    }

    // 전체 회원 정보 조회
    public List<Member> findAll() {
        // Map의 모든 값들을 꺼내서 새로운 ArrayList에 담아서 반환한다.
        // 새로운 ArrayList에 담는 과정을 통해 store에 대한 직접 접근을 막는다.
        return new ArrayList<>(store.values());
    }

    // 회원 저장소 내 데이터 삭제
    public void clearStore() {
        store.clear();
    }
}
