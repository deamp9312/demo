package bord.demo.repository;


import bord.demo.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository  extends JpaRepository<Member,Long> {

    List<Member> findByName(String name);
    Optional<Member> findOptionalByUsername(String name);

    @Query("select m from Member m where m.name=name and m.password=:password")
    public Optional<Member> userCheck(@Param("name")String name,@Param("password")String password);

}
