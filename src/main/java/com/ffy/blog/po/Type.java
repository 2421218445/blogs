package com.ffy.blog.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 分类实体类
 */
/*
    @OneToMany(mappedBy = "type") 代表多对一
        mappedBy 指定 （一） 的名字 , 被维护的关系
 */
@Entity
@Table(name = "t_type")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Type {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();

    public Type() {
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
