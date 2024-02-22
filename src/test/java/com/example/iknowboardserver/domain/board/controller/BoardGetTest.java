package com.example.iknowboardserver.domain.board.controller;

import com.example.iknowboardserver.SpringBootTestClass;
import com.example.iknowboardserver.domain.board.controller.form.BoardPostRequestForm;
import com.example.iknowboardserver.domain.board.entity.Board;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("게시글 조회 테스트")
public class BoardGetTest extends SpringBootTestClass {
    @Nested
    @DisplayName("클라이언트가 게시글을 조회를 요청하면")
    class Describe_request_board_get {
        Long id;
        String title;
        String content;

        @BeforeEach
        void setUp() {
            Board board = Board.builder()
                    .title(RandomStringUtils.random(10, true, true))
                    .content(RandomStringUtils.random(200, true, true))
                    .build();
            board = boardRepository.save(board);
            id = board.getId();
        }

        @Nested
        @DisplayName("게시글이 존재하면")
        class Context_with_board_exists {
            @Test
            @DisplayName("게시글이 반환된다")
            void it_returns_board() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/board/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(id))
                        .andExpect(jsonPath("$.title").value(title))
                        .andExpect(jsonPath("$.content").value(content));
            }
        }

        @Nested
        @DisplayName("게시글이 존재하지 않으면")
        class Context_with_no_board {
            Long id = 0L;

            @Test
            @DisplayName("404 에러를 반환한다")
            void it_returns_404Error() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/board/" + id))
                        .andExpect(status().isNotFound());
            }
        }
    }
}
