package com.example.demo.member.dao;

import java.util.List;

import com.example.demo.member.dto.MemberDTO;


public interface MemberDAO {

	void joinMember(MemberDTO memberDTO);

	List<MemberDTO> memberList();


	int detailMember(MemberDTO dto);


	MemberDTO detailMember1(String id);

	int findMyPwd(String pwd, String id);

	int delMember(MemberDTO dto);

	int login(String id, String pwd);

}
