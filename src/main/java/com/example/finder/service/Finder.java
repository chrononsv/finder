package com.example.finder.service;

import com.example.finder.model.Member;
import com.example.finder.model.MembersGroup;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Интерфейс поиска Members в списке групп
 * @author SVNikitin
 */
public interface Finder {

    /**
     * Метод для создания фильтра
     * @param age - Возраст для фильтрации
     * @return Возвращет фильтр
     */
    static Predicate<Member> isAgeLessThan(Integer age) {
        return p -> p.getAge() < age;
    }

    /**
     *
     * @deprecated Do not use this method!
     */
    @Deprecated
    Set<String> findMembers(List<MembersGroup> listMembers,Integer age);

    /**
     * Метод поиска Members по фильтру
     * @param groups - List groups
     * @param predicate - Filter predicate
     * @return Возвращает множество Member
     */
    Set<Member> findMembersStream(List<MembersGroup> groups, Predicate<Member> predicate);
}
