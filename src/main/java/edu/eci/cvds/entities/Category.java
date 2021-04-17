package edu.eci.cvds.entities;

import java.util.Date;

public class Category {
    private int id;
    private String name;
    private String description;
    private Date creationDate;
    private boolean status;
    private Date modificationDate;

    public Category() {
        super();
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;

    }

    public Category(int id, String name, String description, Date creationDate, boolean status, Date modificationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.status = status;
        this.modificationDate = modificationDate;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }
}
