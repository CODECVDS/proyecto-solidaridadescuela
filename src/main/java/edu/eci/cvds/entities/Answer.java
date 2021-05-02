package edu.eci.cvds.entities;

import java.util.Date;

public class Answer {
    private int id;
    private String name;
    private Date creationDate;
    private String comments;
    private String answerTo;
    private int toId;

    public Answer() {
        super();
    }

    public Answer(int id, String name, Date creationDate, String comments, String answerTo, int toId) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.comments = comments;
        this.answerTo = answerTo;
        this.toId = toId;
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

    public String getAnswerTo() {
        return answerTo;
    }

    public void setAnswerTo(String answerTo) {
        this.answerTo = answerTo;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }
}

