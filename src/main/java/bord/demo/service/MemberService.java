package bord.demo.service;


import bord.demo.domain.entity.Member;
import bord.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    //회원가입
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);        //중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION     //member.getName()을 멀티 스레드 때문에 중복네임이 들어와서 오류날것을 생각해 유니크 제약조건으로 잡아줄 것
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }

    }


    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public List<Member> findMemberByName(String name){
        return memberRepository.findByName(name);
    }


    public Member login(String Name,String password){
        List<Member> byName = memberRepository.findByName(Name);
        Member member = byName.get(0);
        if(member.getPassword().equals(password)){
            return member;
        }else{
            return null;
        }

    }





}
