package bord.demo.controller;

import bord.demo.domain.entity.Member;

import bord.demo.repository.MemberRepository;
import bord.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    //회원가입--
    @GetMapping("/members/new")
    public String create(@ModelAttribute("memberForm") MemberForm form) {
        log.info("getmapping/members/new");

        return "members/createAccountForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {
        log.info("postmapping/members/new");

        if (result.hasErrors()) {
            return "members/createAccountForm";
        }

        Member member = new Member(form.getName(),form.getPassword());
        memberService.join(member);
        log.info(member.getName());
        log.info(member.getPassword());

        return "redirect:/";

    }
//--


    //로그인
    @GetMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginForm form) {
        log.info("getmapping/members/login");

        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = memberService.login(form.getName(), form.getPassword());
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }


 /*       Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
        response.addCookie(idCookie);*/

        return "redirect:/login/loginMain";
    }

    @GetMapping("/login/loginMain")
    public String loginHome(){
        return "/login/loginMain";
    }
/*
    @GetMapping("/boards/boards")
    public String gotoBoards(){
        return "/boards/boards";
    }*/


}
