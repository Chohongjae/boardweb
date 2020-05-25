package com.chohongjae.boardweb.service;

import com.chohongjae.boardweb.domain.Board;
import com.chohongjae.boardweb.domain.Search;
import com.chohongjae.boardweb.persistence.BoardRepository;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Override
    public void insertBoard(Board board) {
        boardRepository.save(board);
    }

    @Override
    public void updateBoard(Board board) {
        Board newBoard = boardRepository.findById(board.getSeq()).get();

        newBoard.setContent(board.getContent());
        newBoard.setTitle(board.getTitle());
        boardRepository.save(newBoard);
    }

    @Override
    public void deleteBoard(Board board) {
        boardRepository.deleteById(board.getSeq());
    }

    @Override
    public Board getBoard(Board board) {
        return boardRepository.findById(board.getSeq()).get();
    }

    @Override
    public Page<Board> getBoardList(Search search) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QBoard qBoard = QBoard.board;

        if (search.getSearchCondition().equals("TITLE")){
            booleanBuilder.and(qBoard.title.like("%" + search.getSearchKeyword() + "%"));
        } else if(search.getSearchCondition().equals("CONTENT")){
            booleanBuilder.and(qBoard.content.like("%"+ search.getSearchKeyword() + "%"));
        }
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "seq");
        return boardRepository.findAll(booleanBuilder, pageable);
    }
}
