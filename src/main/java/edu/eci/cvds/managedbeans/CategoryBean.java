package edu.eci.cvds.managedbeans;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.SolidaridadServices;
import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
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
    private boolean button;

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
        if (this.category.getId() == 0) {
            register();
        }
        else {
            update();
        }
        PrimeFaces.current().executeScript("PF('manageCategoryDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-categories");
    }

    public void openNew() {
        this.category = new Category();
        category.setId(0);
        category.setStatus(true);
    }

    public Category getCategory() throws ServicesException {
        if (category == null && categoryId != null){
            category = solidaridadServices.loadCategory(categoryId);
        }
        return category;
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
}
