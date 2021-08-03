package bord.demo.dto;

import lombok.Data;

@Data
public class MemberDto {

    private Long id;
    private String name;
    private String board;

    public MemberDto(Long id, String username, String board) {
        this.id = id;
        this.name = username;
        this.board = board;
    }
}
