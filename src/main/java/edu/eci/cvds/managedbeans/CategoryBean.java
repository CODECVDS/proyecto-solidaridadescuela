package edu.eci.cvds.managedbeans;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.SolidaridadServices;
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
    private Integer categoriaId;
    private Category category;
    private String name;
    private String description;
    private int id;
    private Date creationDate;
    private Date modificationDate;

    public void loadCategory() throws Exception{
        try {
            if(categoriaId != null){
                category = solidaridadServices.loadCategory(categoriaId);
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

    public void register() throws Exception{
        try {
            category = new Category(name, description);
            solidaridadServices.registerCategory(category);
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


    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Category getCategory() throws Exception{
        if (category == null && categoriaId != null){
            category = solidaridadServices.loadCategory(categoriaId);
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
