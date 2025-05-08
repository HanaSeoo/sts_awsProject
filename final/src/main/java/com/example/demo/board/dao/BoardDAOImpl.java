package com.example.demo.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.board.dto.BoardDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	public List<BoardDTO> boardList(){
		return em.createQuery("select b From Board b order by b.articleNo desc", BoardDTO.class)
				.getResultList();
	}

	@Override
	public List<BoardDTO> myBoardList(String loginId) {
		// TODO Auto-generated method stub
		return em.createQuery("select b from Board b where b.id = :loginId order by b.articleNo desc", BoardDTO.class)
				.setParameter("loginId", loginId)
				.getResultList();
	}
	@Override
	public void insertBoard(BoardDTO dto) {
		// TODO Auto-generated method stub
		// hibernate의 JPA 방식
		em.persist(dto);
	}

	@Override
	public BoardDTO getBoard(int articleNo) {
		// TODO Auto-generated method stub
		 em.createQuery("UPDATE Board b SET b.viewCount = b.viewCount + 1 WHERE b.articleNo = :articleNo")
          .setParameter("articleNo", articleNo)
          .executeUpdate();
		BoardDTO dto = em.createQuery
				 ("select b From Board b where b.articleNo = :articleNo", BoardDTO.class)
				 .setParameter("articleNo", articleNo)
				 .getSingleResult();
		
		return dto;
	}

	@Override
	public int modBoard(BoardDTO boardDTO) {
	    return em.createQuery("UPDATE Board b SET b.title = :title, b.content = :content WHERE b.id = :id")
	             .setParameter("title",boardDTO.getTitle())
	             .setParameter("content", boardDTO.getContent())
	             .setParameter("id", boardDTO.getId())
	             .executeUpdate();
	}

	@Override
	public int delBoard(int artcleNo) {
		// TODO Auto-generated method stub
		   return em.createQuery("DELETE FROM Board b WHERE b.articleNo = :articleNo")
			        .setParameter("articleNo", artcleNo)
			        .executeUpdate();
	}


	
}