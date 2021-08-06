package bord.demo.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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

    protected Member() {
    }

    public Member(String name, String password ) {
        this.name = name;
        this.password = password;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(getName(), member.getName()) && Objects.equals(getPassword(), member.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPassword());
    }
}
