package edu.eci.cvds.entities;

import java.sql.Date;


public class Need {
    private int id;
    private int category;
    private String name;
    private String description;
    private String urgency;
    private Date creationDate;
    private Status status;
    private Date modificationDate;

    public Need(){
        super();
    }

    public Need(int id, int category, String name, String description, String urgency, Date creationDate, Status status, Date modificationDate){
        this.id=id;
        this.category=category;
        this.name=name;
        this.description=description;
        this.urgency=urgency;
        this.creationDate=creationDate;
        this.status=status;
        this.modificationDate=modificationDate;
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

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
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
