package com.example.demo.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.member.dao.MemberDAO;
import com.example.demo.member.dto.MemberDTO;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
@Autowired
MemberDAO dao;
@Autowired
private PasswordEncoder passwordEncoder; 
@Override
public void joinMember(MemberDTO dto) {
    // 비밀번호 암호화
    String encodedPwd = passwordEncoder.encode(dto.getPwd());
    dto.setPwd(encodedPwd);
    
    dao.joinMember(dto);
}
	@Override
	public List<MemberDTO> memberList() {
		// TODO Auto-generated method stub
		return dao.memberList();
	}
	@Override
	public int detailMember(MemberDTO dto) {
		// TODO Auto-generated method stub
		return dao.detailMember(dto);
	}
	@Override
	public MemberDTO detailMember1(String id) {
		// TODO Auto-generated method stub
		return dao.detailMember1(id);
	}
	@Override
	public int findMyPwd(String pwd, String id) {
		// TODO Auto-generated method stub
		return dao.findMyPwd(pwd,id);
	}
	@Override
	public int delMember(MemberDTO dto) {
		// TODO Auto-generated method stub
		return dao.delMember(dto);
	}
	@Override
	public int login(String id, String pwd) {
		// TODO Auto-generated method stub
		return dao.login(id, pwd);
	}
	
	
}
