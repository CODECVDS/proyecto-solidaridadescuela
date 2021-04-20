package edu.eci.cvds.managedbeans;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.SolidaridadServices;
import org.primefaces.PrimeFaces;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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

    public void loadCategory() throws Exception{
        try {
            if(categoryId != null){
                category = solidaridadServices.loadCategory(categoryId);
            }
        } catch (ServicesException ex){
            throw ex;
        }
    }

    public List<Category> getCategories() throws Exception{
        try {
            return solidaridadServices.loadCategories();
        } catch (ServicesException ex){
            throw ex;
        }
    }

    public void update() throws Exception{
        try {
            solidaridadServices.updateCategory(category);
        } catch (ServicesException ex){
            throw ex;
        }
    }

    public  void register() throws ServicesException {
        try {
            solidaridadServices.registerCategory(category);
        } catch (ServicesException ex){
            throw ex;
        }
    }

    public void save() throws Exception {
        if (this.category.getId() == 0) {
            register();
            System.out.println("Registrando");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Category Added"));
        }
        else {
            update();
            System.out.println("Actualizando");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Category Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageCategoryDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-categories");
    }

    public void openNew() {
        this.category = new Category();
        category.setId(0);
    }


    public Integer getCategoriaId() {
        return categoryId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoryId = categoriaId;
    }

    public Category getCategory() throws Exception{
        if (category == null && categoryId != null){
            category = solidaridadServices.loadCategory(categoryId);
        }
        return category;
    }

    public void setCategory(Category category) {
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
