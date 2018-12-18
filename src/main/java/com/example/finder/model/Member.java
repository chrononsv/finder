package com.example.finder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс Member со свойствами <b>id</b>, <b>name</b>, <b>age</b>, <b>group</b>.
 * @author SVNikitin
 */
@Entity
@Table(name = "MEMBERS")
public class Member {

    /** Уникальный идентификатор */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Наименование */
    @Column
    private String name;

    /** Возраст */
    @Column
    private Integer age;

    /** Ссылка на группу */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private MembersGroup group;

    public Member() {
    }

    public Member(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public MembersGroup getGroup() {
        return group;
    }

    public void setGroup(MembersGroup group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(name, member.name) &&
                Objects.equals(age, member.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}