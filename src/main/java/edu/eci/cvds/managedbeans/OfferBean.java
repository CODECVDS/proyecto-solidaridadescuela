package edu.eci.cvds.managedbeans;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.entities.Offer;
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

@ManagedBean(name = "offerBean")
@SessionScoped
public class OfferBean  extends BasePageBean {

    @Inject
    private SolidaridadServices solidaridadServices;
    private Offer offer;
    private Integer offerId;
    private int id;
    private int category;
    private String name;
    private String description;
    private Date creationDate;
    private Status status;
    private Date modificationDate;
    private String username;
    private List<Category> categories;
    private List<Status> allStatus;
    private boolean hide;

    public void save(){
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        offer.setUsername(session.getAttribute("username").toString());
        if (this.offer.getId() == 0) {

        }
        else {
            update();
        }
        PrimeFaces.current().executeScript("PF('manageOfferDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-offers");
    }
    public void openNew() {
        this.offer = new Offer();
    }

    public void update(){
        try {
            solidaridadServices.updateOffer(offer);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Offer Updated"));
        } catch (ServicesException ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Update Error", "Update Error"));
        }
    }

    public List<Offer> getOffers() throws ServicesException{
        try {
            return solidaridadServices.loadOffers();
        } catch (ServicesException ex){
            throw ex;
        }
    }


    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Offer getOffer() throws ServicesException{
        if (offer == null && offerId != null){
            offer = solidaridadServices.loadOffer(offerId);
        }
        return offer;
    }

    public List<Category> getCategories() throws ServicesException {
        try {
            categories = solidaridadServices.loadActiveCategories(true);
        } catch (ServicesException e) {
            throw e;
        }
        return categories;
    }

    public List<Status> getAllStatus() {
        return Arrays.asList(status.values());
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
}
