package edu.eci.cvds.managedbeans;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.entities.CountStatus;
import edu.eci.cvds.entities.Need;
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
import java.util.*;

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
    private List<Need> needsWS;
    private PieChartModel pieModel;
    private List<CountStatus> needsbyStatus;
    private HashMap<Integer,String> urgenciesHm;

    public  void register(){
        try{
            if(solidaridadServices.loadCategory(need.getCategory()).getIsValid()){
                solidaridadServices.registerNeed(need);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Need Added"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Categoria Invalida",solidaridadServices.loadCategory(need.getCategory()).getDescriptinvalid()));
            }
        } catch (ServicesException ex){
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Add Error",ex.getMessage()));
        }
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
            ex.getCause().getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Update Error", ex.getMessage()));
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

    public HashMap<Integer,String> getUrgenciesHm(){
        urgenciesHm = new HashMap<Integer,String>();
        urgenciesHm.put(1,"Primary");
        urgenciesHm.put(2,"success");
        urgenciesHm.put(3,"info");
        urgenciesHm.put(4,"warning");
        urgenciesHm.put(5,"danger");
        return urgenciesHm;
    }

    public String getStringUg(Integer i){
        getUrgenciesHm();
        return urgenciesHm.get(i);
    }

    public void setUrgenciesHm(HashMap<Integer, String> urgenciesHm) {
        this.urgenciesHm = urgenciesHm;
    }

    public List<Category> getCategories() throws ServicesException {
        return solidaridadServices.loadActiveCategories(true);
    }

    public Integer getNeedId() {
        return needId;
    }

    public void setNeedId(Integer needId) {
        this.needId = needId;
    }

    public List<Need> getNeeds() throws ServicesException {
        return solidaridadServices.loadNeeds();
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

    private void createpieModel()  {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();

        try {
            needsbyStatus = solidaridadServices.loadNeedsbyStatus();
        } catch (ServicesException e) {
            e.printStackTrace();
        }

        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();
        needsbyStatus.stream().forEach(p -> values.add(p.getConteo()));
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

    public boolean editable(String needN){
        currentUser = SecurityUtils.getSubject();
        session = currentUser.getSession();
        return currentUser.hasRole("Administrator")?false:!needN.equals(session.getAttribute("username"));
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

    public List<CountStatus> getNeedsbyStatus() {
        return needsbyStatus;
    }

    public void setNeedsbyStatus(List<CountStatus> needsbyStatus) {
        this.needsbyStatus = needsbyStatus;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public PieChartModel getPieModel() {
        createpieModel();
        return pieModel;
    }

    public SolidaridadServices getSolidaridadServices() {
        return solidaridadServices;
    }

    public void setSolidaridadServices(SolidaridadServices solidaridadServices) {
        this.solidaridadServices = solidaridadServices;
    }

    public List<Need> getNeedsWS() {
        try {
            needsWS = solidaridadServices.loadNeedsWS();
        } catch (ServicesException ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al cargar necesidades", ex.getMessage()));
        }
        return needsWS;
    }

    public void setNeedsWS(List<Need> needsWS) {
        this.needsWS = needsWS;
    }
}
