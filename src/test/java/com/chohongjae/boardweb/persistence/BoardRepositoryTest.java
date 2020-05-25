package com.chohongjae.boardweb.persistence;

import com.chohongjae.boardweb.domain.Board;
import com.chohongjae.boardweb.domain.Member;
import com.chohongjae.boardweb.domain.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void testInsert() {
        Member member = new Member();
        member.setEnabled(true);
        member.setId("member");
        member.setName("둘리");
        member.setRole(Role.ROLE_MEMBER);
        member.setPassword(passwordEncoder.encode("member123"));

        Member member2 = new Member();
        member2.setEnabled(true);
        member2.setId("admin");
        member2.setName("도우너");
        member2.setRole(Role.ROLE_ADMIN);
        member2.setPassword(passwordEncoder.encode("admin123"));

        for(int i=1; i<=13; i++){
            Board board = new Board();
            board.setMember(member);
            board.setTitle(member.getName()+"가 등록한 게시물"+i);
            board.setContent(member.getName()+"가 등록한 게시물"+i);
        }

        memberRepository.save(member);


        for(int i=1; i<=13; i++){
            Board board = new Board();
            board.setMember(member2);
            board.setTitle(member2.getName()+"가 등록한 게시물"+i);
            board.setContent(member2.getName()+"가 등록한 게시물"+i);
        }

        memberRepository.save(member2);
    }

    @Test
    public void testGetBoard(){
        Board board = boardRepository.findById(1L).get();

        System.out.println(board.toString());
        System.out.println(board.getMember().getName());
    }

    @Test
    public void testGetMember(){
        Member member = memberRepository.findById("member").get();
        for(Board board: member.getBoardList()){
            System.out.println(board.toString());
        }
    }
}