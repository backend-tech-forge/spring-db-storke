package com.example.iknowboardserver.domain.board.controller.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardPutRequestForm {
    private String title;
    private String content;
}
