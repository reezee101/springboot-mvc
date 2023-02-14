package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();//싱글톤이라 객체 new 해서 생성 불가

    //test 끝날때마다 초기화
    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

//    @Test
//    void save(){
//        //given
//        Member member = new Member("고양", 2);
//
//        //when
//        Member savedMember = memberRepository.save(member);
//
//        //then
//        Member findMember = memberRepository.findById(savedMember.getId());
//            //찾아온 멤버는 저장된 멤버와 같아야 한다
//        assertThat(findMember).isEqualTo(savedMember);
//    }

    @Test
    void findAll(){
        //given
        Member member1 = new Member("개", 3);
        Member member2 = new Member("강쥐", 4);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> allMember = memberRepository.findAll();

        //then
        assertThat(allMember.size()).isEqualTo(2);
        assertThat(allMember).contains(member1, member2);
    }
}