package com.revature.forum.models;

import com.revature.annotations.*;


@Table(name="boards")
public class Board {

    @PrimaryKey(name="id")
    public int id;
    @Column(name="name")
    public String name;
    @Column(name="description")
    public String description;


    public Board(int id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.description = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }
}
