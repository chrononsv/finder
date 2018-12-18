package com.example.finder.repository;

import com.example.finder.model.MembersGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий MemberGroup
 */
public interface MemberGroupRepository extends JpaRepository<MembersGroup,Integer> {
}
