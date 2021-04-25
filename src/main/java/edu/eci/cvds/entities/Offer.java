package edu.eci.cvds.entities;

import java.util.Date;

public class Offer {
    private int id;
    private int category;
    private String name;
    private String description;
    private Date creationDate;
    private Status status;
    private Date modificationDate;
    private String username;

    public Offer(){ super(); }

    public Offer(int id, int category, String name, String description, Date creationDate, Status status, Date modificationDate, String username) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.status = status;
        this.modificationDate = modificationDate;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }
}
