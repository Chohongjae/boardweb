package com.chohongjae.boardweb.service;

import com.chohongjae.boardweb.domain.Board;
import com.chohongjae.boardweb.domain.Search;
import org.springframework.data.domain.Page;

public interface BoardService {

    void insertBoard(Board board);
    void updateBoard(Board board);
    void deleteBoard(Board board);
    Board getBoard(Board board);
    Page<Board> getBoardList(Search search);
}
