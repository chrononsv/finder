package com.example.finder.controller;

import com.example.finder.FinderApplication;
import com.example.finder.model.Member;
import com.example.finder.model.MembersGroup;
import com.example.finder.repository.MemberGroupRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FinderApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class FinderControllerIntegrationTest {

    private static final Logger logger = LogManager.getLogger(FinderControllerIntegrationTest.class);

    @Autowired
    MemberGroupRepository memberGroupRepository;

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    private static Member memberStive = new Member( "Stive", 23);
    private static Member memberDave = new Member( "Dave", 45);
    private static Member memberMark = new Member( "Mark", 55);

    private static String stiveGrp = "StiveGrp";


    @Before
    public void setup() {

        memberGroupRepository.deleteAll();

        MembersGroup membersGroup = new MembersGroup();
        List<Member> members = new ArrayList<>();
        members.add(memberStive);
        members.add(memberDave);
        members.add(memberMark);
        membersGroup.setGroupName(stiveGrp);
        membersGroup.setMembers(members);

        membersGroup.getMembers().forEach((member) -> member.setGroup(membersGroup));

        int grpId = memberGroupRepository.save(membersGroup).getId();
        logger.info("grpId:" + grpId);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void testFindController() {
        HttpEntity<Set<Member>> entity = new HttpEntity<>(null, headers);

        ResponseEntity responseEntity = restTemplate.exchange(createURLWithPort("/find/byagelessthan/50"),
                HttpMethod.GET, entity, Set.class);

        ObjectMapper mapper = new ObjectMapper();
        Set<Member> members = mapper.convertValue(responseEntity.getBody(), new TypeReference<Set<Member>>() {});

        Assert.assertTrue(members.contains(memberStive));
        Assert.assertFalse(members.contains(memberMark));
    }
}