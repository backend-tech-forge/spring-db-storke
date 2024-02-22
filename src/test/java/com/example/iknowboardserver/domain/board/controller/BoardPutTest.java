package com.example.iknowboardserver.domain.board.controller;

import com.example.iknowboardserver.SpringBootTestClass;
import com.example.iknowboardserver.domain.board.controller.form.BoardPostRequestForm;
import com.example.iknowboardserver.domain.board.controller.form.BoardPutRequestForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("게시글 수정 테스트")
public class BoardPutTest extends SpringBootTestClass {
    @Nested
    @DisplayName("클라이언트가 게시글을 수정을 요청하면")
    class Describe_request_board_put {
        BoardPutRequestForm reqForm;
        Long boardId;
        @Nested
        @DisplayName("제목과 내용을 입력하면")
        class Context_with_title_and_content {
            @BeforeEach
            void setUp() throws Exception {
                BoardPostRequestForm postReqForm = new BoardPostRequestForm();
                postReqForm.setTitle(RandomStringUtils.random(10));
                postReqForm.setContent(RandomStringUtils.random(200));

                mockMvc.perform(MockMvcRequestBuilders.post("/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postReqForm)))
                        .andExpect(status().isOk())
                        .andDo(result -> {
                            JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
                            boardId = Long.parseLong(jsonNode.get("id").asText());
                        });

                reqForm = new BoardPutRequestForm();
                reqForm.setTitle(RandomStringUtils.random(10));
                reqForm.setContent(RandomStringUtils.random(200));

            }

            @Nested
            @DisplayName("게시글이 존재하면")
            class Context_with_board_exists {
                @Test
                @DisplayName("게시글이 수정된다")
                void It_updates_board() throws Exception {
                    mockMvc.perform(MockMvcRequestBuilders.put("/board/" + boardId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(reqForm)))
                            .andExpect(status().isOk());
                }
            }
            @Nested
            @DisplayName("게시글이 존재하지 않으면")
            class Context_with_no_board {
                @Test
                @DisplayName("404 에러를 반환한다")
                void it_returns_404Error() throws Exception {
                    mockMvc.perform(MockMvcRequestBuilders.put("/board/0")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(reqForm)))
                            .andExpect(status().isNotFound());
                }
            }
        }

    }
}
