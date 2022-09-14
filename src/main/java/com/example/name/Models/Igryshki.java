package com.example.name.Models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Igryshki
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    public Igryshki(String title, String material, String author, Integer size, Integer price) {
        this.title = title;
        this.material = material;
        this.author = author;
        this.size = size;
        this.price = price;
    }
    public Igryshki() {

    }

    String title, material, author;

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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    Integer size, price;
}
