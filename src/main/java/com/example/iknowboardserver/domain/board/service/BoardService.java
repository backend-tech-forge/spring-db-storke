package com.example.iknowboardserver.domain.board.service;

import com.example.iknowboardserver.domain.board.controller.form.BoardDTO;
import com.example.iknowboardserver.domain.board.controller.form.BoardPostRequestForm;
import com.example.iknowboardserver.domain.board.controller.form.BoardPutRequestForm;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface BoardService {
    ResponseEntity<Map<String, Object>> createBoard(BoardPostRequestForm reqForm);

    ResponseEntity<BoardDTO> getBoard(Long id);

    ResponseEntity<Map<String, Object>> updateBoard(Long id, BoardPutRequestForm reqForm);

    ResponseEntity<Map<String, String>> deleteBoard(Long id);
}
