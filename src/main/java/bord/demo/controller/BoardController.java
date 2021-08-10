package bord.demo.controller;

import bord.demo.domain.entity.Board;
import bord.demo.domain.entity.Member;
import bord.demo.repository.BoardJpaRepository;
import bord.demo.repository.BoardRepository;
import bord.demo.service.BoardSerivce;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardRepository boardRepository;

    private final BoardSerivce boardSerivce;

    private final BoardJpaRepository boardJpaRepository;



    @GetMapping("/board/boards")
    public String boards(Model model){
        List<Board> board = boardRepository.findAll();

        model.addAttribute("board", board);
        return "board/boards";
    }


    //글 추가
    @GetMapping("/board/new")
    public String create(@ModelAttribute("boardsForm") BoardsForm form) {
        log.info("getmapping/board/new");

        return "board/addBoard";
    }

    @PostMapping("/board/new")
    public String create(@Valid BoardsForm form, BindingResult result) {
        log.info("postmapping/board/new");

        if (result.hasErrors()) {
            return "board/addBoard";
        }

        Board board = new Board(form.getTitle(), form.getContext());

        boardRepository.save(board);


        log.info(board.getTitle());

        return "redirect:/board/boards";

    }





    //번호 맵핑 아직 구현 안됨
    @GetMapping("/board/boardnum/{boardId}")
    public String item(@PathVariable long boardId, Model model) {
        log.info("getmapping/board/boardnum/{itemId}");

        List<Board> board = boardRepository.findAllById(Collections.singleton(boardId));
/*        Long visitcount = boards.get(0).getVisitcount();
        if(visitcount!=null) {
            visitcount++;
        }else {
            visitcount=1L;
        }
        boards.get(0).setVisitcount(visitcount);*/

        model.addAttribute("board", board);

        return "board/boardnum";
    }

//수정기능
    @GetMapping("/board/boardnum/{boardId}/edit")
    public String editForm(@PathVariable Long boardId,Model model){
        Optional<Board> board = boardRepository.findById(boardId);
        model.addAttribute("board", board);
        return "board/editBoard";

    }

    @PostMapping("/board/boardnum/{boardId}/edit")
    public String edit(@PathVariable Long boardId, Model model) {
//        @ModelAttribute(name = "board") Board board
//        boardJpaRepository.update(boardId, board);

        Optional<Board> board = boardRepository.findById(boardId);
//        Board boards = boardRepository.save(board);


        model.addAttribute("board", board);



        log.info(board.get().getTitle());
        log.info(board.get().getContext());
//        return "redirect: /board/boards";
//        return "board/boards/1";
        return "login/loginMain";
    }






    //사전 데이터
    @PostConstruct
    public void init() {

        boardRepository.save(new Board("Atitle", "aaaa" ,0L));
        boardRepository.save(new Board("Btitle", "bbbb",0L));
    }



}
