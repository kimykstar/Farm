package Repository;

import Domain.Member;

import java.util.Optional;

public interface MemberRepositoryInterface {
    // user찾기
    Optional<Member> findMemberById(String id);
    // 회원가입
    boolean join(Member member);
    // 회원정보 수정
    boolean update(Member member);
    // 회원 탈퇴
    boolean delete(Member member);
    String getPwHash(String id);


}
