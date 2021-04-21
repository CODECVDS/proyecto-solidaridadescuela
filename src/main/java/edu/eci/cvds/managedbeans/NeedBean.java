package edu.eci.cvds.managedbeans;



import edu.eci.cvds.entities.Category;
import edu.eci.cvds.entities.Need;
import edu.eci.cvds.entities.Status;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.SolidaridadServices;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@ManagedBean(name = "needBean")
@SessionScoped
public class NeedBean extends BasePageBean{
    @Inject
    private SolidaridadServices solidaridadServices;

    private Need need;

    private Integer category;

    private String name;

    private String description;

    private Integer urgency;

    private Status status;

    private Integer needId;

    public void register() throws ServicesException{
        System.out.println("intento");
        try{
            this.need=new Need();
            System.out.println("ya no se puede cancelar");
            solidaridadServices.registerNeed(need);
        }catch(ServicesException ex){
            //throw ex;
            ex.printStackTrace();
        }
    }

    public Integer getNeedId() {
        return needId;
    }

    public void setNeedId(Integer needId) {
        this.needId = needId;
    }

    public void update() throws Exception{
        try {
            solidaridadServices.updateNeed(need);
        } catch (ServicesException ex){
            throw ex;
        }
    }

    public List<Need> getNeeds() throws ServicesException{
        try {
            return solidaridadServices.loadNeeds();
        } catch (ServicesException ex){
            throw ex;
        }
    }

    public void loadNeed() throws ServicesException{
        try {
            if(needId != null){
                need = solidaridadServices.loadNeed(needId);
            }
        } catch (ServicesException ex){
            throw ex;
        }
    }

    public Need getNeed() throws ServicesException{
        System.out.println(needId);
        need = solidaridadServices.loadNeed(needId);
        return need;
    }

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
