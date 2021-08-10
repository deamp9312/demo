package bord.demo.service;


import bord.demo.domain.entity.Board;
import bord.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardSerivce {
    private BoardRepository boardRepository;

    @Transactional
    public Long create(Board board) {

        boardRepository.save(board);

        return board.getId();
    }


}
