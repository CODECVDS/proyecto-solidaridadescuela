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
import java.time.LocalDate;

@ManagedBean(name = "needBean")
@RequestScoped
public class NeedBean extends BasePageBean{
    @Inject
    private SolidaridadServices solidaridadServices;

    private Need need;

    private Integer category;

    private String name;

    private String description;

    private Integer urgency;

    private Status status;

    public void register() throws Exception{
        try{
            need = new Need(category, name, description, urgency, status);
            solidaridadServices.registerNeed(need);
        }catch(ServicesException ex){
            throw ex;
        }
    }

    public Need getNeed() { return need; }

    public void setNeed(Need need) {
        this.need = need;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
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

    public Integer getUrgency() {
        return urgency;
    }

    public void setUrgency(Integer urgency) {
        this.urgency = urgency;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
