package bord.demo.dto;

import lombok.Data;

@Data
public class BoardDto {

    private String title;
    private String context;
    private String member;

    public BoardDto(String textname, String context, String member) {
        this.title = textname;
        this.context = context;
        this.member = member;
    }
}
