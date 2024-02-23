package com.example.iknowboardserver.domain.board.service;

import com.example.iknowboardserver.domain.board.controller.form.BoardDTO;
import com.example.iknowboardserver.domain.board.controller.form.BoardPostRequestForm;
import com.example.iknowboardserver.domain.board.controller.form.BoardPutRequestForm;
import com.example.iknowboardserver.domain.board.entity.Board;
import com.example.iknowboardserver.domain.board.repository.BoardRepository;
import com.example.iknowboardserver.global.error.ErrorCode;
import com.example.iknowboardserver.global.error.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    final BoardRepository boardRepository;

    @Override
    public ResponseEntity<Map<String, Object>> createBoard(BoardPostRequestForm reqForm) {
        Board board = Board.builder()
                .title(reqForm.getTitle())
                .content(reqForm.getContent())
                .build();

        board = boardRepository.save(board);
        return ResponseEntity.ok(Map.of("id", board.getId()));
    }

    @Override
    public ResponseEntity<BoardDTO> getBoard(Long id) {
        Optional<Board> maybeBoard = boardRepository.findById(id);
        if (maybeBoard.isEmpty()) {
            throw new GlobalException(ErrorCode.BOARD_NOT_FOUND);
        }
        Board board = maybeBoard.get();
        return ResponseEntity.ok(BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build());
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateBoard(Long id, BoardPutRequestForm reqForm) {
        Optional<Board> maybeBoard = boardRepository.findById(id);
        if (maybeBoard.isEmpty()) {
            throw new GlobalException(ErrorCode.BOARD_NOT_FOUND);
        }
        Board board = maybeBoard.get();
        board.setTitle(reqForm.getTitle());
        board.setContent(reqForm.getContent());
        board = boardRepository.save(board);
        return ResponseEntity.ok(Map.of("id", board.getId()));
    }


    @Override
    public ResponseEntity<Map<String, String>> deleteBoard(Long id) {
        Optional<Board> maybeBoard = boardRepository.findById(id);
        if (maybeBoard.isEmpty()) {
            throw new GlobalException(ErrorCode.BOARD_NOT_FOUND);
        }
        boardRepository.deleteById(id);
        return ResponseEntity.ok(Map.of("id", id.toString(), "status", "deleted"));
    }
}
