package com.example.name.Models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Zoo
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    public Zoo(String title, String name, String author, Integer world, Integer live)
    {
        this.title = title;
        this.name = name;
        this.author = author;
        this.world = world;
        this.live = live;
    }
    public Zoo() {

    }

    String title, name, author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getWorld() {
        return world;
    }

    public void setWorld(Integer world) {
        this.world = world;
    }

    public Integer getLive() {
        return live;
    }

    public void setLive(Integer live) {
        this.live = live;
    }

    Integer world, live;
}
