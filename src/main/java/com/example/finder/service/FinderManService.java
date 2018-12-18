package com.example.finder.service;

import com.example.finder.model.Member;
import com.example.finder.model.MembersGroup;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Сервис поиска Members в списке групп
 * @author SVNikitin
 */
@Service
public  class FinderManService implements Finder {

    /**
     *
     * @deprecated Do not use this method!
     */
    @Override
    @Deprecated
    public Set<String> findMembers(List<MembersGroup> groups,Integer age) {
        Set<String> groupsNames = new HashSet<>();
        for (MembersGroup membersGroup : groups) {
            for (Member member : membersGroup.getMembers()) {
                if (member.getAge() > age) {
                    String name = member.getName();
                    groupsNames.add(name);
                }
            }
        }
        return groupsNames;
    }

    /**
     * Метод поиска Members по фильтру
     * @param groups - List groups
     * @param predicate - Filter predicate
     * @return Возвращает моножество Member
     */
    @Override
    public Set<Member> findMembersStream(List<MembersGroup> groups, Predicate<Member> predicate) {
        return groups.stream().flatMap(membersGroup -> membersGroup.getMembers().stream()).filter(predicate).collect(Collectors.toSet());
    }
}
