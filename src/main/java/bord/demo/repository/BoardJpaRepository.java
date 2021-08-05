package bord.demo.repository;

import bord.demo.domain.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class BoardJpaRepository {

    @Autowired
    EntityManager em;

    BoardRepository boardRepository;

    public void update(Long boardId, Board update){
        Optional<Board> byId = boardRepository.findById(boardId);
        byId.get().setTitle(update.getTitle());
        byId.get().setContext(update.getContext());

    }
}
