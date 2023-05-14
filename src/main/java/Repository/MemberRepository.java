package Repository;

import Domain.Member;

import java.util.Optional;

public class MemberRepository implements MemberRepositoryInteface{

    @Override
    public Optional<Member> findMemberById(String id) {

        return null;
    }

    @Override
    public Member join(Member member) {
        return null;
    }

    @Override
    public Member update(Member member) {
        return null;
    }

    @Override
    public boolean delete(Member member) {
        return false;
    }
}
