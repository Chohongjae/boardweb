package com.chohongjae.boardweb.controller;

import com.chohongjae.boardweb.domain.Board;
import com.chohongjae.boardweb.domain.Search;
import com.chohongjae.boardweb.security.SecurityUser;
import com.chohongjae.boardweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/")
public class BoardController {
    @Autowired
    BoardService boardService;

    @RequestMapping("/getBoardList")
    public String getBoardList(Model model, Search search){
        if (search.getSearchCondition() == null){
            search.setSearchCondition("TITLE");
        }
        if (search.getSearchKeyword() == null){
            search.setSearchKeyword("");
        }
        Page<Board> boardPage = boardService.getBoardList(search);
        model.addAttribute("boardList", boardPage);
        return "/board/getBoardList";
    }

    @RequestMapping("/getBoard")
    public String getBoard(Model model, Board board){
        model.addAttribute("board", boardService.getBoard(board));
        return "/board/getBoard";
    }

    @GetMapping("/insertBoard")
    public String insertBoardView(){
        return "/board/insertBoard";
    }

    @PostMapping("/insertBoard")
    public String insertBoard(Board board, @AuthenticationPrincipal SecurityUser securityUser){
        board.setMember(securityUser.getMember());
        boardService.insertBoard(board);
        return "redirect:/board/getBoardList";
    }

    @PostMapping("/updateBoard")
    public String updateBoard(Board board){
        boardService.updateBoard(board);
        return "forward:/board/getBoardList";
    }

    @GetMapping("/deleteBoard")
    public String deleteBoard(Board board){
        boardService.deleteBoard(board);
        return "forward:/board/getBoardList";
    }


}
