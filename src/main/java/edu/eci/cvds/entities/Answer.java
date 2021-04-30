package edu.eci.cvds.entities;

import java.util.Date;

public class Answer {
    private int id;
    private String name;
    private Date creationDate;
    private String comments;
    private int offer;
    private int need;

    public Answer() {
        super();
    }

    public Answer(int id, String name, Date creationDate, String comments, int offer, int need) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.comments = comments;
        this.offer = offer;
        this.need = need;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getOffer() {
        return offer;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }

    public int getNeed() {
        return need;
    }

    public void setNeed(int need) {
        this.need = need;
    }
}

