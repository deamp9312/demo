package bord.demo.controller;

import bord.demo.domain.entity.Member;
import bord.demo.repository.MemberRepository;
import bord.demo.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberControllerTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;


    @Test
    public void member() throws Exception{

        Member member = new Member("abc","123123");
        Member member2 = new Member("ddd","123123");


        Member savemember = memberRepository.save(member);
        Member savemember2 = memberRepository.save(member2);

        List<Member> findmember = memberRepository.findByName("abc");

        Assertions.assertThat(findmember.get(0).getName()).isEqualTo(member.getName());


    }




}