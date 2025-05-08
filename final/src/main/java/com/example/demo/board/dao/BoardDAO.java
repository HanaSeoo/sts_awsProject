package com.example.demo.board.dao;

import java.util.List;

import com.example.demo.board.dto.BoardDTO;

public interface BoardDAO {
	List<BoardDTO> boardList();
	void insertBoard(BoardDTO dto);
	BoardDTO getBoard(int articleNo);
	int modBoard(BoardDTO boardDTO);
	int delBoard(int artcleNo);
	List<BoardDTO> myBoardList(String loginId);
}