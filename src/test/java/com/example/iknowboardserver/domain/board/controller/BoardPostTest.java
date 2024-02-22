package com.example.iknowboardserver.domain.board.controller;

import com.example.iknowboardserver.SpringBootTestClass;
import com.example.iknowboardserver.domain.board.controller.form.BoardPostRequestForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("게시글 작성 테스트")
public class BoardPostTest extends SpringBootTestClass {
    @Nested
    @DisplayName("클라이언트가 게시글을 작성을 요청하면")
    class Describe_request_board_post {
        String title;
        String content;
        BoardPostRequestForm reqForm;
        @Nested
        @DisplayName("제목과 내용을 입력하면")
        class Context_with_title_and_content {
            @BeforeEach
            void setUp() {
                title = RandomStringUtils.random(10);
                content = RandomStringUtils.random(200);
                reqForm = new BoardPostRequestForm();
                reqForm.setTitle(title);
                reqForm.setContent(content);
            }

            @Test
            @DisplayName("게시글이 작성된다")
            void It_creates_board() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.post("/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reqForm)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").exists());
                verify(boardRepository, times(1)).save(any());
            }
        }
    }

}
