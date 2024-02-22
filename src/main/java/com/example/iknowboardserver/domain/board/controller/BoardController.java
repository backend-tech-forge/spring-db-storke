package com.example.iknowboardserver.domain.board.controller;

import com.example.iknowboardserver.domain.board.controller.form.BoardDTO;
import com.example.iknowboardserver.domain.board.controller.form.BoardPostRequestForm;
import com.example.iknowboardserver.domain.board.controller.form.BoardPutRequestForm;
import com.example.iknowboardserver.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createBoard(@RequestBody BoardPostRequestForm reqForm) {
        return boardService.createBoard(reqForm);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BoardDTO> getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateBoard(@PathVariable Long id, @RequestBody BoardPutRequestForm reqForm) {
        return boardService.updateBoard(id, reqForm);
    }
    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
    }
}
