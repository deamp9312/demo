package bord.demo.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Entity
public class Member {

    @GeneratedValue @Id
    @Column(name = "user_id")
    private Long id;


    private String name;
    private String password;

/*
    @Enumerated(EnumType.STRING)
    private Rank rank;
*/

    @OneToMany(mappedBy = "member")
    private List<Board> boards = new LinkedList<>();

    public Member() {
    }

    public Member(String name, String password ) {
        this.name = name;
        this.password = password;

    }





}
