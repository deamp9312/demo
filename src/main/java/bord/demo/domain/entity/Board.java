package bord.demo.domain.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String context;


    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public Board() {
    }

    public Board(String textname, String context) {
        this.title = textname;
        this.context = context;
    }
}
