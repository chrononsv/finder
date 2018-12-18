package com.example.finder.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс MembersGroup со свойствами <b>id</b>, <b>groupName</b>, <b>members</b>.
 * @author SVNikitin
 */
@Entity
@Table(name = "MEMBERS_GROUP")
public class MembersGroup {

    /** Уникальный идентификатор */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Наименование группы */
    @Column
    private String groupName;

    /** Список Member */
    @OneToMany(
            mappedBy = "group",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Member> members = new ArrayList<>();

    public MembersGroup() {
    }

    public MembersGroup( String groupName, List<Member> members) {
        this.groupName = groupName;
        this.members = members;
    }

    public int getId() {
        return id;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}