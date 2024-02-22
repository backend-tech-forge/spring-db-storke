package com.example.iknowboardserver.domain.board.controller.form;

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
}
