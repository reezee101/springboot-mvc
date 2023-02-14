package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();  //동시성 문제 고려하지 않음 -> 실무에선 ConcurrentHashMap, AtomicLong 사용
    private static long sequance = 0L;
    //MemberRepository 안에 인스턴스들이 다 static이므로 MemberRepository 아무리 new 해도 변수는 한번만 생성됨

    //싱글톤
    private static final MemberRepository instance = new MemberRepository();
    public static MemberRepository getInstance(){
        return instance;
    }
    private MemberRepository(){}

    public Member save(Member member){
        member.setId(++sequance);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values()); //store안에 있는 모든 값들을 ArrayList에 담아 넘김
    }

    public void clearStore(){
        store.clear();
    }

}
