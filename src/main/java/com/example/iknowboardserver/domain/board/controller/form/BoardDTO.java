package com.example.iknowboardserver.domain.board.controller.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class BoardDTO {
    private Long id;
    private String title;
    private String content;
    private String createdAt;
    private String updatedAt;
}
