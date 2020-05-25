package com.chohongjae.boardweb.security;

import com.chohongjae.boardweb.domain.Member;
import com.chohongjae.boardweb.persistence.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<Member> optional = memberRepository.findById(s);
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException(s + "사용자 없음");
        } else {
            Member member = optional.get();
            return new SecurityUser(member);
        }
    }
}
