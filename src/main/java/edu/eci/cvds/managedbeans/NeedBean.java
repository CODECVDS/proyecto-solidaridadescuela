package edu.eci.cvds.managedbeans;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.entities.Need;
import edu.eci.cvds.entities.Status;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.SolidaridadServices;
import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean(name = "needBean")
@SessionScoped
public class NeedBean extends BasePageBean{
    @Inject
    private SolidaridadServices solidaridadServices;
    private Need need;
    private int id;
    private int category;
    private String name;
    private String description;
    private int urgency;
    private Date creationDate;
    private Status status;
    private Date modificationDate;
    private List<Status> allStatus;
    private List<Integer> categories;

    public List<Integer> getCategories() throws ServicesException {
        List<Category> list = new ArrayList<Category>();
        try {
            list = solidaridadServices.loadCategories();
            categories = list.stream().map(Category::getId).collect(Collectors.toList());
        } catch (ServicesException e) {
            throw e;
        }
        return categories;
    }

    public List<Status> getAllStatus() {
        return Arrays.asList(status.values());
    }


    public List<Need> getNeeds() throws ServicesException {
        try {
            return solidaridadServices.loadNeeds();
        } catch (ServicesException ex){
            ex.printStackTrace();
            throw ex;
        }
    }

    public  void register(){
        try{
            solidaridadServices.registerNeed(need);
        } catch (ServicesException ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Need Error"));
        }

    }

    public void save() throws ServicesException {
        register();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Need Added"));
        PrimeFaces.current().executeScript("PF('manageNeedDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-needs");
    }

    public void openNew() {
        this.need = new Need();
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

    public int getUrgency() {
        return urgency;
    }

    public void setUrgency(int urgency) {
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
