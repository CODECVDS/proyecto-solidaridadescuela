package edu.eci.cvds.managedbeans;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.entities.ReportCategory;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.SolidaridadServices;
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
import java.util.Date;
import java.util.List;


@ManagedBean(name = "categoryBean")
@SessionScoped
public class CategoryBean extends BasePageBean {

    @Inject
    private SolidaridadServices solidaridadServices;
    private Integer categoryId;
    private Category category;
    private String name;
    private String description;
    private int id;
    private Date creationDate;
    private Date modificationDate;
    private boolean isValid;
    private String descriptinvalid;
    private boolean button;
    private List<ReportCategory> report;
    private PieChartModel pieModel;

    public void loadCategory() throws ServicesException{
        try {
            if(categoryId != null){
                category = solidaridadServices.loadCategory(categoryId);
            }
        } catch (ServicesException ex){
            throw ex;
        }
    }

    public List<Category> getCategories() throws ServicesException{
        try {
            return solidaridadServices.loadCategories();
        } catch (ServicesException ex){
            throw ex;
        }
    }


    public void update(){
        try {
            solidaridadServices.updateCategory(category);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Category Updated"));
        } catch (ServicesException ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Update Error", "Update Error"));
        }
    }

    public  void register(){
        try {
            solidaridadServices.registerCategory(category);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Category Added"));
        } catch (ServicesException ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Add Error","Add Error"));
        }
    }

    public void save() throws ServicesException {
        if (this.category.getId() ==    0) {
            register();
        }
        else {
            update();
        }
        PrimeFaces.current().executeScript("PF('manageCategoryDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-categories");
    }

    public void erase(Category c) throws ServicesException {
        try{
            solidaridadServices.deleteCategory(c);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Category Deleted"));
        }catch(ServicesException ex){
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete Error","Delete Error"));
        }
    }

    public void openNew() {
        this.category = new Category();
        category.setStatus(true);
    }

    public Category getCategory() throws ServicesException {
        if (category == null && categoryId != null){
            category = solidaridadServices.loadCategory(categoryId);
        }
        return category;
    }

    private void createpieModel()  {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();

        try {
            report = solidaridadServices.loadReportCategory();
        } catch (ServicesException e) {
            e.printStackTrace();
        }

        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();
        report.stream().forEach(p -> values.add(p.getTotal()));
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(82, 190, 128)");
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgb(193, 86, 255)");
        bgColors.add("rgb(255, 86, 86)");

        for (int i=5; i < report.size(); i++){
            bgColors.add(bgColors.get(i-5));
        }
        dataSet.setBackgroundColor(bgColors);
        data.addChartDataSet(dataSet);

        List<String> labels = new ArrayList<>();
        report.stream().forEach(p -> labels.add(p.getCategory()));
        data.setLabels(labels);

        pieModel.setData(data);
    }

    public boolean isButton() {
        return button;
    }

    public void setButton(boolean button) {
        this.button = button;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public List<ReportCategory> getReport() {
        try {
            report = solidaridadServices.loadReportCategory();
        } catch (ServicesException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",e.getMessage()));
        }
        return report;
    }

    public void setReport(List<ReportCategory> report) {
        this.report = report;
    }

    public PieChartModel getPieModel() {
        createpieModel();
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getDescriptinvalid() {
        return descriptinvalid;
    }

    public void setDescriptinvalid(String descriptinvalid) {
        this.descriptinvalid = descriptinvalid;
    }
}
