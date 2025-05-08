package com.example.demo.member.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.board.dto.BoardDTO;
import com.example.demo.member.dto.MemberDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private PasswordEncoder passwordEncoder; 

    
	
	
	@Override
	public void joinMember(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		em.persist(memberDTO);
	}
	
	@Override
	public List<MemberDTO> memberList(){ 
		return em.createQuery("select b From Member b order by b.id desc", MemberDTO.class)
				.getResultList();
	}
	
	@Override
	public MemberDTO detailMember1(String id) {
		// TODO Auto-generated method stub
		MemberDTO dto = em.createQuery
				 ("select b From Member b where b.id = :id", MemberDTO.class)
				 .setParameter("id", id)
				 .getSingleResult();
		
		return dto;
	}
	
	@Override
	public int detailMember(MemberDTO dto) {
	    return em.createQuery("UPDATE Member b SET b.pwd = :pwd, b.name = :name, b.email = :email WHERE b.id = :id")
	             .setParameter("pwd",dto.getPwd())
	             .setParameter("name", dto.getName())
	             .setParameter("email", dto.getEmail())
	             .setParameter("id", dto.getId())
	             .executeUpdate();
	}

	@Override
	public int findMyPwd(String pwd, String id) {
	    Long count = em.createQuery("SELECT COUNT(m) FROM Member m WHERE m.id = :id AND m.pwd = :pwd", Long.class)
	        .setParameter("id", id)
	        .setParameter("pwd", pwd)
	        .getSingleResult();
	    
	    return count.intValue(); 
}

	@Override
	public int delMember(MemberDTO dto) {
		// TODO Auto-generated method stub
		return em.createQuery("DELETE FROM Member b WHERE b.id = :id")
				.setParameter("id", dto.getId())
				.executeUpdate();
	}

	@Override
	public int login(String id, String pwd) {
		// TODO Auto-generated method stub
		 String storedPwd = em.createQuery("SELECT m.pwd FROM Member m WHERE m.id = :id", String.class)
                 .setParameter("id", id)
                 .getSingleResult();

		 return passwordEncoder.matches(pwd, storedPwd) ? 1 : 0;
	}
}
