package com.jshoppractice.jshoppractice.controller;

import com.jshoppractice.jshoppractice.form.MemberForm;
import com.jshoppractice.jshoppractice.domain.Address;
import com.jshoppractice.jshoppractice.domain.Member;
import com.jshoppractice.jshoppractice.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        log.info("createForm");

        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result){
        log.info("member create ");

        if(result.hasErrors()){
            return "members/createMemberForm";
        }

        Member member = new Member();
        Address address = new Address(memberForm.getCity(),
                memberForm.getStreet(),
                memberForm.getZipcode());

        member.setName(memberForm.getName());
        member.setAddress(address);

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String members(Model model){
        log.info("member List");

        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
