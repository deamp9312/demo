package bord.demo.controller;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BoardsForm {

//    private String name;

    private Long id;

    @NotEmpty(message = "제목은 빈칸으로 할수없습니다")
    private String title;
    private String context;

    private int visitcount; //조회수

}
