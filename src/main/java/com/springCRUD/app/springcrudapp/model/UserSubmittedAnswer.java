package com.springCRUD.app.springcrudapp.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserSubmittedAnswer {
    private Integer id;
    private String answer;
}
