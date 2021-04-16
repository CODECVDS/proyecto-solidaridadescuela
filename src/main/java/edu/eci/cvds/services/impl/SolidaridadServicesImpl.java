package edu.eci.cvds.services.impl;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.entities.Need;
import edu.eci.cvds.persistence.NeedDAO;
import edu.eci.cvds.persistence.CategoryDAO;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.SolidaridadServices;

import javax.inject.Inject;

public class SolidaridadServicesImpl implements SolidaridadServices {

    //@Inject
    private NeedDAO needDAO;

    @Inject
    private CategoryDAO categoryDAO;

    @Override
    public void registerCategory(Category c) throws ServicesException {
        try {
            categoryDAO.registerCategory(c);
        }catch (PersistenceException ex){
            throw new ServicesException("Error al crear categoria",ex);
        }
    }

    @Override
    public void updateCategory(String name, String description, boolean status) throws ServicesException {
        try {
            categoryDAO.updateCategory(name,description,status);
        }catch (PersistenceException ex){
            throw new ServicesException("Error al actualizar la categoria",ex);
        }
    }

    @Override
    public void registerNeed(Need need) throws ServicesException{
        try{
            needDAO.save(need);
        }catch(PersistenceException ex){
            throw new ServicesException("Error al crear la necesidad",ex);
        }
    }


}
