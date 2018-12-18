package com.example.finder.repository;

import com.example.finder.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий Members
 */
public interface MemberRepository extends JpaRepository<Member,Integer> {
}
