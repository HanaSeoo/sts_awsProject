package com.example.demo.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.board.dao.BoardDAO;
import com.example.demo.board.dto.BoardDTO;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDAO dao;
	
	@Override
	public List<BoardDTO> boardList() {
		// TODO Auto-generated method stub
		return dao.boardList();
	}
	@Override
	public List<BoardDTO> myBoardList(String loginId) {
		// TODO Auto-generated method stub
		return dao.myBoardList(loginId);
	}

	@Override
	public void insertBoard(BoardDTO dto) {
		// TODO Auto-generated method stub
		dao.insertBoard(dto);
	}

	@Override
	public BoardDTO getBoard(int articleNo) {
		// TODO Auto-generated method stub
		return dao.getBoard(articleNo);
	}

	@Override
	public int modBoard(BoardDTO boardDTO) {
		// TODO Auto-generated method stub
		return dao.modBoard(boardDTO);
	}

	@Override
	public int delBoard(int ArtcleNo) {
		// TODO Auto-generated method stub
		return dao.delBoard(ArtcleNo);
	}

}


