package edu.eci.cvds.managedbeans;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.entities.CountStatus;
import edu.eci.cvds.entities.Offer;
import edu.eci.cvds.entities.Status;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.SolidaridadServices;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.primefaces.PrimeFaces;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.ArrayList;
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
    private Category c;
    private Subject currentUser;
    private Session session;
    private PieChartModel pieModel;
    private List<CountStatus> offerbyStatus;

    public  void register(){

        try {
            solidaridadServices.registerOffer(offer);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Offer Added"));
        } catch (ServicesException ex){
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Add Error",ex.getMessage()));
        }
    }

    public void update(){
        try {
            solidaridadServices.updateOffer(offer);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Offer Updated"));
        } catch (ServicesException ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Update Error", ex.getMessage()));
        }
    }

    public void save(){
        currentUser = SecurityUtils.getSubject();
        session = currentUser.getSession();
        offer.setUsername(session.getAttribute("username").toString());
        if (this.offer.getId() == 0) {
            register();
        }
        else {
            update();
        }
        PrimeFaces.current().executeScript("PF('manageOfferDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-offers");
    }

    private void createpieModel()  {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();

        try {
            offerbyStatus = solidaridadServices.loadOfferbyStatus();
        } catch (ServicesException e) {
            e.printStackTrace();
        }

        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();
        offerbyStatus.stream().forEach(p -> values.add(p.getConteo()));
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(82, 190, 128)");
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgb(55, 105, 255)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Active");
        labels.add("Closed");
        labels.add("InProcess");
        labels.add("Solved");
        data.setLabels(labels);

        pieModel.setData(data);
    }
    public void openNew() {
        this.offer = new Offer();
    }

    public List<Offer> getOffers() throws ServicesException{
        return solidaridadServices.loadOffers();
    }

    public List<Offer> getOffersWS() throws ServicesException{
        return solidaridadServices.loadAllOffersWS();
    }

    public Category getC(int cId) throws ServicesException {
        return solidaridadServices.loadCategory(cId);
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

    public boolean editable(String offerN){
        currentUser = SecurityUtils.getSubject();
        session = currentUser.getSession();
        return currentUser.hasRole("Administrator")?false:!offerN.equals(session.getAttribute("username"));
    }

    public List<Category> getCategories() throws ServicesException {
        return solidaridadServices.loadActiveCategories(true);
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

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public PieChartModel getPieModel() {
        createpieModel();
        return pieModel;
    }
}
