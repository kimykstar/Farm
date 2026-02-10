package com.Hanium.Farm.Farm.Repository;


import com.Hanium.Farm.Farm.Vo.Member;

public interface MemberRepositoryInterface {
    boolean join(Member member);
    boolean delete(String id);
    Member getMember(String id);
}
