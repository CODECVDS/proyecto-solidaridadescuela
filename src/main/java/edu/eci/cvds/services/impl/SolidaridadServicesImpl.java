package edu.eci.cvds.services.impl;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.entities.Need;
import edu.eci.cvds.entities.User;
import edu.eci.cvds.persistence.NeedDAO;
import edu.eci.cvds.persistence.CategoryDAO;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.UserDAO;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.SolidaridadServices;

import javax.inject.Inject;
import java.util.List;

public class SolidaridadServicesImpl implements SolidaridadServices {

    @Inject
    private NeedDAO needDAO;

    @Inject
    private CategoryDAO categoryDAO;

    @Inject
    private UserDAO userDAO;

    @Override
    public void registerCategory(Category c) throws ServicesException {
        try {
            categoryDAO.registerCategory(c);
        }catch (PersistenceException ex){
            throw new ServicesException("Error al crear categoria",ex);
        }
    }

    @Override
    public void updateCategory(Category category) throws ServicesException {
        try {
            categoryDAO.updateCategory(category);
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

    @Override
    public void updateNeed(Need need) throws ServicesException {
        try{
            needDAO.updateNeed(need);
        }catch(PersistenceException ex){
            throw new ServicesException("Error al actualizar la necesidad",ex);
        }
    }

    @Override
    public List<Need> loadNeeds() throws ServicesException{
        try{
            return needDAO.needs();
        }catch(PersistenceException ex){
            throw new ServicesException("Error al cargar las necesidades",ex);
        }
    }

    @Override
    public Need loadNeed(int needId) throws ServicesException{
        try {
            return needDAO.load(needId);
        } catch (PersistenceException ex) {
            throw new ServicesException("Error al cargar categoria",ex);
        }
    }

    @Override
    public Category loadCategory(int categoryId) throws ServicesException {
        try {
            return categoryDAO.load(categoryId);
        } catch (PersistenceException ex) {
            throw new ServicesException("Error al cargar categoria",ex);
        }
    }

    @Override
    public List<Category> loadCategories() throws ServicesException {
        try {
            return categoryDAO.loadAll();
        } catch (PersistenceException ex) {
            throw new ServicesException("Error al cargar las categorias",ex);
        }
    }

    @Override
    public User getUser(String username) throws ServicesException {
        try {
            return userDAO.getUser(username);
        } catch (PersistenceException ex) {
            throw new ServicesException("Error al cargar el usuario",ex);
        }
    }


}
