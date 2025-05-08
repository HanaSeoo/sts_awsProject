package com.example.demo.board.service;

import java.util.List;

import com.example.demo.board.dto.BoardDTO;

public interface BoardService {
	List<BoardDTO> boardList();
	void insertBoard(BoardDTO dto);
	BoardDTO getBoard(int articleNo);
	int modBoard(BoardDTO boardDTO);
	int delBoard(int articleNo);
	List<BoardDTO> myBoardList(String loginId);
}