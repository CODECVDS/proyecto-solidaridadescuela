package edu.eci.cvds.services.impl;

import edu.eci.cvds.entities.*;
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
    /*
    @Inject
    private UserDAO userDAO;
    */
    @Inject
    private OfferDAO offerDAO;

    @Inject
    private AnswerDAO answerDAO;

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
    public void deleteCategory(Category category) throws ServicesException {
        try{
            categoryDAO.deleteCategory(category);
        }catch (PersistenceException ex){
            throw new ServicesException("Error al eliminar la categoria",ex);
        }
    }

    @Override
    public void registerNeed(Need need) throws ServicesException{
        try{
            needDAO.save(need);
        }catch(PersistenceException ex){
            throw new ServicesException(ex.getMessage(),ex);
        }
    }

    @Override
    public void updateNeedStatus(Need need) throws ServicesException {
        try{
            needDAO.updateNeedStatus(need);
        }catch(PersistenceException ex){
            throw new ServicesException("Error al actualizar la necesidad",ex);
        }
    }

    @Override
    public void registerOffer(Offer offer) throws ServicesException {
        try {
            offerDAO.registerOffer(offer);
        }catch (PersistenceException ex){
            throw new ServicesException(ex.getMessage(),ex);
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
    public List<Offer> loadAllOffersWS() throws ServicesException {
        try {
            return offerDAO.loadAllOffersWS();
        } catch (PersistenceException ex) {
            throw new ServicesException("Error al cargar ofertas por estado",ex);
        }
    }

    @Override
    public int loadParamNOffer() throws ServicesException {
        try {
            return offerDAO.loadParamNOffer();
        } catch (PersistenceException ex){
            throw new ServicesException("Error al cargar parametro NOffer",ex);
        }
    }

    @Override
    public List<Answer> loadAnswers() throws ServicesException {
        try {
            return answerDAO.answers();
        } catch (PersistenceException ex){
            throw new ServicesException("Error al cargar las respuestas ",ex);
        }
    }

    @Override
    public void registerAnswer(Answer answer) throws ServicesException {
        try {
            answerDAO.save(answer);
        } catch (PersistenceException ex){
            ex.printStackTrace();
            throw new ServicesException("Error al cargar las respuestas ",ex);
        }

    }

    @Override
    public List<CountStatus> loadNeedsbyStatus() throws ServicesException {
        try {
            return needDAO.needsbyStatus();
        }catch (PersistenceException ex){
            throw new ServicesException("Error al cargar necesidades por status",ex);
        }
    }



    @Override
    public List<Need> loadNeedsWS() throws ServicesException {
        try {
            return needDAO.loadNeedsWS();
        }catch (PersistenceException ex){
            throw new ServicesException("Error al cargar las necesidades en",ex);
        }
    }

    @Override
    public List<CountStatus> loadOfferbyStatus() throws ServicesException {
        try {
            return offerDAO.loadOfferbyStatus();
        }catch (PersistenceException ex){
            throw new ServicesException("Error al cargar reporte ofertas",ex);
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
    public List<ReportCategory> loadReportCategory() throws ServicesException {
        try {
            return categoryDAO.loadReportCategory();
        } catch (PersistenceException ex) {
            throw new ServicesException("Error al cargar el reporte de categorias",ex);
        }
    }

    /*
    @Override
    public User getUser(String username) throws ServicesException {
        try {
            return userDAO.getUser(username);
        } catch (PersistenceException ex) {
            throw new ServicesException("Error al cargar el usuario",ex);
        }
    }
    */



}
