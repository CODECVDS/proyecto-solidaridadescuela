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
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

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

    public void update() throws Exception{
        try {
            solidaridadServices.updateNeed(need);
        } catch (ServicesException ex){
            throw ex;
        }
    }

    public void onRowEdit(RowEditEvent event) throws Exception {
        update();
        FacesMessage msg = new FacesMessage("Product Edited");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onAddNew() throws Exception {
        // Add one new product to the table:
        register();
        FacesMessage msg = new FacesMessage("New Product added");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<Need> getNeeds() throws Exception{
        try {
            return solidaridadServices.loadNeeds();
        } catch (ServicesException ex){
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
