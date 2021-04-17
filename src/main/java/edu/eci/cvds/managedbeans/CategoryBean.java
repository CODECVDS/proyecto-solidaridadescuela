package edu.eci.cvds.managedbeans;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.SolidaridadServices;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.util.Date;

@ManagedBean(name = "categoryBean")
@RequestScoped
public class CategoryBean {

    private Category category;
    private String name;
    private String description;

    @Inject
    private SolidaridadServices solidaridadServices;

    @ManagedProperty(value = "#{param.categoria}")
    private Integer categoriaId;

    private String nombre;
    private String descripcion;

    //Metodos registrar y actualizar

    public void loadCategory() {
        try {
            if(categoriaId != null){
                category = solidaridadServices.loadCategory(categoriaId);
            }
        } catch (ServicesException ex){

        }
    }

    public void update() throws Exception{
        try {
            solidaridadServices.updateCategory(category);
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    public void register() throws Exception{
        try {
            category = new Category(2,name,descripcion,new Date(04/15/2021), true, new Date(04/15/2021));
            solidaridadServices.registerCategory(category);
        } catch (ServicesException ex){
            throw ex;
        }
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
