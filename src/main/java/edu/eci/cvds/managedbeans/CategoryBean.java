package edu.eci.cvds.managedbeans;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.SolidaridadServices;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.Date;

@ManagedBean(name = "categoryBean")
@SessionScoped
public class CategoryBean extends BasePageBean {

    @Inject
    private SolidaridadServices solidaridadServices;
    private Integer categoriaId;
    private Category category;
    private String name;
    private String description;

    public void loadCategory() throws Exception{
        try {
            if(categoriaId != null){
                category = solidaridadServices.loadCategory(categoriaId);
            }
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
}
