package com.chohongjae.boardweb.persistence;

import com.chohongjae.boardweb.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String> {
}
