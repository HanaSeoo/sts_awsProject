package com.example.demo.member.service;

import java.util.List;

import com.example.demo.member.dto.MemberDTO;

public interface MemberService {

	void joinMember(MemberDTO dto);

	List<MemberDTO> memberList();

	int detailMember(MemberDTO dto);

	MemberDTO detailMember1(String id);

	int findMyPwd(String pwd, String id);

	int delMember(MemberDTO dto);

	int login(String id, String pwd);

	

}
