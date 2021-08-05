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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

        Member member = new Member();
        member.setName(form.getName());
        member.setPassword(form.getPassword());
        memberService.join(member);
        log.info(member.getName());
        log.info(member.getPassword());

        return "redirect:/";

    }
//--

    @GetMapping("/members/login")
    public String login(@ModelAttribute("loginForm") LoginForm form) {
        log.info("getmapping/members/login");

        return "login/logIn";
    }
//로그인 검증 부분인데 수정중...
   /* @PostMapping("/members/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/")String redirectURL
            , HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.info("loginerror");
            return "login/logIn";
        }



        Member loginMember = MemberService.login(form.getName(), form.getPassword());
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        return "redirect:"+redirectURL;
    }*/

}
