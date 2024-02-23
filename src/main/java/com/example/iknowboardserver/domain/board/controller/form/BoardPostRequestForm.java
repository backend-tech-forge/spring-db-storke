package com.example.iknowboardserver.domain.board.controller.form;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardPostRequestForm {
    private Long id;
    private String title;
    private String content;

    @Builder
    public BoardPostRequestForm(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
