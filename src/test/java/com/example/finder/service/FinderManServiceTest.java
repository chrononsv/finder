package com.example.finder.service;

import com.example.finder.model.Member;
import com.example.finder.model.MembersGroup;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class FinderManServiceTest {

    private static Member memberStive = new Member( "Stive", 23);
    private static Member memberDave = new Member( "Dave", 45);
    private static Member memberMark = new Member( "Mark", 55);

    private static List<Member> listMembers0 = new ArrayList<Member>() {
        {
            add(memberStive);
            add(memberDave);
            add(memberMark);
        }
    };

    private static Member memberMax = new Member( "Max", 63);
    private static Member memberPiter = new Member( "Piter", 35);
    private static Member memberAlex = new Member( "Alex", 65);

    private static List<Member> listMembers1 = new ArrayList<Member>() {
        {
            add(memberMax);
            add(memberPiter);
            add(memberAlex);
        }
    };

    private static Member memberFil = new Member( "Fil", 73);
    private static Member memberGreg = new Member( "Greg", 25);
    private static Member memberMarx = new Member( "Marx", 15);

    private static List<Member> listMembers2 = new ArrayList<Member>() {
        {
            add(memberFil);
            add(memberGreg);
            add(memberMarx);
        }
    };


    private static MembersGroup membersGroup0 = new MembersGroup( "zeroGroup", listMembers0);
    private static MembersGroup membersGroup1 = new MembersGroup( "firstGroup", listMembers1);
    private static MembersGroup membersGroup2 = new MembersGroup( "secondGroup", listMembers2);


    private static List<MembersGroup> membersGroupListSt = new ArrayList<MembersGroup>() {{
        add(membersGroup0);
        add(membersGroup1);
        add(membersGroup2);
    }};


    @Test
    public void findMembers() {
        FinderManService finderManService = new FinderManService();
        Set<Member> members  =  finderManService.findMembersStream(membersGroupListSt,Finder.isAgeLessThan(50));
        Assert.assertTrue( members.contains(memberStive));
        Assert.assertTrue( members.contains(memberDave));
        Assert.assertTrue( members.contains(memberPiter));
        Assert.assertTrue( members.contains(memberGreg));

        Assert.assertFalse( members.contains(memberMark));
        Assert.assertFalse( members.contains(memberFil));
        Assert.assertFalse( members.contains(memberAlex));
        Assert.assertFalse( members.contains(memberMax));
    }
}