package com.example.demo.member.comtroller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.member.dto.MemberDTO;

import jakarta.servlet.http.HttpSession;

public interface MemberController {

	String memberList(Model model, MemberDTO dto);

	String getDetailMember(Model model, String id);

	String postDetailMember(Model model, MemberDTO dto);

	String delMemberForm(Model model);


	String delMember(Model model, MemberDTO dto, String id, String pwd);

	String memberForm(String formName);



	String joinMember(Model model, @ModelAttribute("MemberDTO")MemberDTO dto);

	String loginForm(Model model);

	String login(Model model, String id, String pwd);

	String logout(Model model, HttpSession session);

	String joinMemberForm(Model model);







}
