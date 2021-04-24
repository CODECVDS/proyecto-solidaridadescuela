package edu.eci.cvds.managedbeans;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.entities.Need;
import edu.eci.cvds.entities.Status;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.SolidaridadServices;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SessionScoped
@ManagedBean(name = "needBean")
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
    private String username;
    private List<Status> allStatus;
    private List<Category> categories;
    private List<Integer> urgencies;

    public List<Category> getCategories() throws ServicesException {
        try {
            categories = solidaridadServices.loadActiveCategories(true);
        } catch (ServicesException e) {
            throw e;
        }
        return categories;
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Need Added"));
        } catch (ServicesException ex){
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Add Error","Add Error"));
        }

    }


    public void save(){
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        need.setUsername(session.getAttribute("username").toString());
        register();
        PrimeFaces.current().executeScript("PF('manageNeedDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-needs");
    }

    public void openNew() {
        this.need = new Need();
    }


    public List<Integer> getUrgencies() {
        urgencies = Arrays.asList(new Integer[]{1, 2, 3, 4, 5});
        return urgencies;
    }

    public List<Status> getAllStatus() {
        return Arrays.asList(status.values());
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
