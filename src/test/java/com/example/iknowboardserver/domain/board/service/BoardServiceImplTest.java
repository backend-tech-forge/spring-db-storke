package com.example.iknowboardserver.domain.board.service;

import com.example.iknowboardserver.domain.board.controller.form.BoardDTO;
import com.example.iknowboardserver.domain.board.controller.form.BoardPostRequestForm;
import com.example.iknowboardserver.domain.board.entity.Board;
import com.example.iknowboardserver.global.error.ErrorCode;
import com.example.iknowboardserver.global.error.GlobalException;
import com.example.iknowboardserver.initializer.SpyInitializer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BoardService test")
class BoardServiceImplTest extends SpyInitializer {
    @Test
    @DisplayName("valid 게시글 생성 시 id 반환 성공")
    void createBoard() {
        // given
        BoardPostRequestForm req = BoardPostRequestForm.builder()
                .title("title")
                .content("content")
                .build();

        // when
        ResponseEntity<Map<String, Object>> board = boardService.createBoard(req);

        // then
        assertThat(board.getBody().get("id")).isNotNull();
        assertThat(board.getBody().get("id")).isInstanceOf(Long.class);
    }

    @Test
    @DisplayName("invalid 한 id 로 게시글 조회 시 404 에러 반환")
    void testGetBoardButFailed() {
        // given
        Long invalidId = 100L;

        // when & then
        GlobalException exception = assertThrows(GlobalException.class, () -> boardService.getBoard(invalidId));
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.BOARD_NOT_FOUND);
    }

    @Test
    @DisplayName("valid id 게시글 조회 시 성공 반환")
    void testGetBoard() {
        // given

        Board boardStub = Board.builder()
                .title("title")
                .content("content")
                .build();
        Board savedBoard = boardRepository.save(boardStub);

        // when
        ResponseEntity<BoardDTO> board = boardService.getBoard(savedBoard.getId());

        // then
        assertThat(board.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}