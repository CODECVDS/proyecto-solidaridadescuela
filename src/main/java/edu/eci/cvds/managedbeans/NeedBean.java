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
    private Integer needId;
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
    private Category c;
    private Subject currentUser;
    private Session session;
    private boolean hide;
    private List<Need> needs;

    public List<Category> getCategories() throws ServicesException {
        try {
            categories = solidaridadServices.loadActiveCategories(true);
        } catch (ServicesException e) {
            throw e;
        }
        return categories;
    }

    public Integer getNeedId() {
        return needId;
    }

    public void setNeedId(Integer needId) {
        this.needId = needId;
    }

    public List<Need> getNeeds() throws ServicesException {
        try {
            needs = solidaridadServices.loadNeeds();
            return needs;
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
        currentUser = SecurityUtils.getSubject();
        session = currentUser.getSession();
        need.setUsername(session.getAttribute("username").toString());
        if(this.need.getId() == 0){
            register();
        }else{
            updateNeedStatus();
        }
        PrimeFaces.current().executeScript("PF('manageNeedDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-needs");
    }

    public void openNew() {
        this.need = new Need();
    }

    public Category getC(int cId) throws ServicesException {
        return solidaridadServices.loadCategory(cId);
    }

    public List<Integer> getUrgencies() {
        urgencies = Arrays.asList(new Integer[]{1, 2, 3, 4, 5});
        return urgencies;
    }

    public void updateNeedStatus(){
        try{
            if (need.getUsername().equals(session.getAttribute("username")) | currentUser.hasRole("Administrator")) {
                solidaridadServices.updateNeedStatus(need);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Need Updated"));
            }
            else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Update Error", "Not allowed"));
            }
        }catch(ServicesException ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Update Error", "Update Error"));
        }
    }

    public List<Status> getAllStatus() {
        return Arrays.asList(status.values());
    }

    public Need getNeed() throws ServicesException{
        if (need == null && needId != null){
            need = solidaridadServices.loadNeed(needId);
        }
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

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public void setNeeds(List<Need> needs) {
        this.needs = needs;
    }
}
