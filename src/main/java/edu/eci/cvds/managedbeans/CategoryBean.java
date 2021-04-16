package edu.eci.cvds.managedbeans;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.SolidaridadServices;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

@ManagedBean(name = "categoryBean")
@RequestScoped
public class CategoryBean extends BasePageBean {

    @Inject
    private SolidaridadServices solidaridadServices;

    @ManagedProperty(value = "#{param.categoria}")
    private Category category;

    //Metodos registrar y actualizar

    public void update() throws Exception{
        try {
            solidaridadServices.updateCategory(category.getName(),category.getDescription(),category.getStatus());
        } catch (ServicesException ex){
            throw ex;
        }
    }

}
