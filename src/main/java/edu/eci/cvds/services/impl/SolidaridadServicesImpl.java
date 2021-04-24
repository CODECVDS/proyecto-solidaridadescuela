package edu.eci.cvds.services.impl;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.entities.Need;
import edu.eci.cvds.entities.Offer;
import edu.eci.cvds.entities.User;
import edu.eci.cvds.persistence.*;
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

    @Inject
    private OfferDAO offerDAO;

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
    public void registerOffer(Offer offer) throws ServicesException {
        try {
            offerDAO.registerOffer(offer);
        }catch (PersistenceException ex){
            throw new ServicesException("Error al registrar oferta",ex);
        }
    }

    @Override
    public void updateOffer(Offer offer) throws ServicesException {
        try {
            offerDAO.updateOffer(offer);
        }catch (PersistenceException ex){
            throw new ServicesException("Error al actualizar oferta",ex);
        }
    }

    @Override
    public Offer loadOffer(int offerId) throws ServicesException {
        try {
            return offerDAO.loadOffer(offerId);
        } catch (PersistenceException ex) {
            throw new ServicesException("Error al cargar oferta",ex);
        }
    }

    @Override
    public List<Offer> loadOffers() throws ServicesException {
        try {
            return offerDAO.loadAllOffers();
        } catch (PersistenceException ex) {
            throw new ServicesException("Error al cargar ofertas",ex);
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
    public List<Category> loadActiveCategories(boolean status) throws ServicesException {
        try {
            return categoryDAO.loadAllActive(status);
        } catch (PersistenceException ex) {
            throw new ServicesException("Error al cargar categorias activas",ex);
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
