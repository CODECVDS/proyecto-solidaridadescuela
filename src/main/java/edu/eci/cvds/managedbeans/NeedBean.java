package edu.eci.cvds.managedbeans;



import edu.eci.cvds.entities.Need;
import edu.eci.cvds.entities.Status;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.SolidaridadServices;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.sql.Date;

@ManagedBean(name = "categoryBean")
@RequestScoped
public class NeedBean extends BasePageBean{
    @Inject
    private SolidaridadServices solidaridadServices;

    @ManagedProperty(value = "#{param.need}")
    private Need need;

    @ManagedProperty(value = "#{param.id}")
    private int id;

    @ManagedProperty(value = "#{param.category}")
    private int category;

    @ManagedProperty(value = "#{param.name}")
    private String name;

    @ManagedProperty(value = "#{param.description}")
    private String description;

    @ManagedProperty(value = "#{param.urgency}")
    private String urgency;

    @ManagedProperty(value = "#{param.creationdate}")
    private Date creationdate;

    @ManagedProperty(value = "#{param.status}")
    private Status status;

    @ManagedProperty(value = "#{param.modificationdate}")
    private Date modificationdate;

    public void register() throws Exception{
        try{
            need = new Need(id, category, name, description, urgency, creationdate, status, modificationdate);
            solidaridadServices.registerNeed(need);
        }catch(ServicesException ex){
            throw ex;
        }
    }

    public Need getNeed() {
        return need;
    }

    public void setNeed(Need need) {
        this.need = need;
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

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getModificationdate() {
        return modificationdate;
    }

    public void setModificationdate(Date modificationdate) {
        this.modificationdate = modificationdate;
    }
}
