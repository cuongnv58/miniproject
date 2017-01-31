package com.example.dell.mini_project;

/**
 * Created by dell on 1/22/2017.
 */

public class Book {
    private String id;
    private String title;
    private String author;
    private String theloai;
    private String icon;
    private String description;
    private String publish;
    public Book(){

    }
    public Book(String id, String title, String author, String theloai, String icon, String description, String publish) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.theloai = theloai;
        this.icon = icon;
        this.description = description;
        this.publish = publish;
    }
    public String getPublish(){return publish;}
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getTheloai() {
        return theloai;
    }

    public String getIcon() {
        return icon;
    }

    public String getDescription() {
        return description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public void setPublish(String publish){this.publish = publish;}
}


