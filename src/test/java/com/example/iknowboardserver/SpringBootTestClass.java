package com.example.iknowboardserver;

import com.example.iknowboardserver.domain.board.repository.BoardRepository;
import com.example.iknowboardserver.domain.board.service.BoardService;
import com.example.iknowboardserver.initializer.RedisInitializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class SpringBootTestClass extends RedisInitializer {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @SpyBean
    @Autowired
    protected BoardRepository boardRepository;
    @Autowired
    protected BoardService boardService;
}
