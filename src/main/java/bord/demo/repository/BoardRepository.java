package bord.demo.repository;

import bord.demo.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board,Long> {

//    Member findMemberByName(String name);

   Optional<Board> findById(Long id);





}
