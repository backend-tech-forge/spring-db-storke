package com.example.iknowboardserver.initializer;

import com.example.iknowboardserver.domain.board.repository.BoardRepository;
import com.example.iknowboardserver.domain.board.service.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@ExtendWith(RedisInitializer.class)
public class SpyInitializer {
    @SpyBean
    protected BoardRepository boardRepository;
    @SpyBean
    protected BoardService boardService;

    @BeforeEach
    public void setDown() {
        boardRepository.deleteAll();
    }
}
