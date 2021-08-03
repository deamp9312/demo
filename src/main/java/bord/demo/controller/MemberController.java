package bord.demo.controller;

import bord.demo.domain.entity.Member;

import bord.demo.repository.MemberRepository;
import bord.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/members/new")
    public String create(Model model){
        log.info("getmapping/members/new");

        model.addAttribute("memberForm", new MemberForm());
        return "members/createAccountForm";
    }
    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result){
        log.info("postmapping/members/new");

        if(result.hasErrors()){
            return "members/createAccountForm";
        }

        Member member = new Member();
        member.setName(form.getName());
        member.setPassword(form.getPassword());
        memberService.join(member);
        log.info(member.getName());
        log.info(member.getPassword());

        return "redirect:/";

    }

    @GetMapping("/members/login")
    public String login(Model model){
        log.info("getmapping/members/login");
        model.addAttribute("loginForm", new MemberForm());
        return "login/logIn";
    }

    @PostMapping("/members/login")
    public String login(@Valid MemberForm form,BindingResult result){
        if(result.hasErrors()){
            return "login/logIn";
        }

        Optional<Member> beforeMember = memberRepository.userCheck(form.getName(), form.getPassword());
        if(beforeMember!=null){
            return
        }



//고쳐야됨
        return "ok";


    }

}
