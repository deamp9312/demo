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
        List<Board> boards = boardRepository.findAll();

        model.addAttribute("boards", boards);
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


    //번호 맵핑
    @GetMapping("/board/boards/{boardId}")
    public String item(@PathVariable long boardId, Model model) {
        log.info("getmapping/board/{itemId}");

        Optional<Board> boards = boardRepository.findById(boardId);
        Long visitcount = boards.get().getVisitcount();
        if(visitcount!=null) {
            visitcount++;
        }else {
            visitcount=1L;
        }

        boards.get().setVisitcount(visitcount);
        model.addAttribute("boards", boards);
        return "board/boards";
    }




    //사전 데이터
    @PostConstruct
    public void init() {

        boardRepository.save(new Board("Atitle", "aaaa"));
        boardRepository.save(new Board("Btitle", "bbbb"));
    }



}
