package Repository;

import Domain.Member;

import java.util.Optional;

public interface MemberRepositoryInteface {
    // user찾기
    Optional<Member> findMemberById(String id);
    // 회원가입
    Member join(Member member);
    // 회원정보 수정
    Member update(Member member);
    // 회원 탈퇴
    boolean delete(Member member);



}
