package com.example.finder.controller;

import com.example.finder.model.Member;
import com.example.finder.repository.MemberGroupRepository;
import com.example.finder.service.Finder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * Класс контроллер с полями <b>memberGroupRepository</b>, <b>finderManService</b>.
 * @author SVNikitin
 */
@Controller
public class FinderController {

    /** Хранилище групп */
    private final MemberGroupRepository memberGroupRepository;

    /** Сервис поиска */
    private final Finder finderManService;

    /**
     * Конструктор
     * @param memberGroupRepository Хранилище MemberGroup
     * @param finderManService Сервис поиска по фильтру
     */
    @Autowired
    public FinderController(MemberGroupRepository memberGroupRepository, Finder finderManService) {
        this.memberGroupRepository = memberGroupRepository;
        this.finderManService = finderManService;
    }

    /**
     * REST Controller получения множества Member из листа MemberGroup по фильтру age
     * @param age - Возраст
     * @return Возвращает
     */
    @GetMapping(value = "/find/byagelessthan/{age}")
    public ResponseEntity<Set<Member>> devices(@PathVariable("age") Integer age) {
        Set<Member> res = finderManService.findMembersStream(memberGroupRepository.findAll(), Finder.isAgeLessThan(age));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
