package edu.eci.cvds.services;

import edu.eci.cvds.entities.*;

import java.util.List;

public interface SolidaridadServices {

    public void registerCategory(Category c) throws ServicesException;

    public void updateCategory(Category category) throws ServicesException;

    public void registerNeed(Need need) throws ServicesException;

    public void updateNeedStatus(Status status) throws ServicesException;

    public void registerOffer(Offer offer) throws ServicesException;

    public void updateOffer(Offer offer) throws ServicesException;

    public Offer loadOffer(int offerId) throws ServicesException;

    public Category loadCategory(int categoryId) throws ServicesException;

    public List<Category> loadCategories() throws ServicesException;

    public User getUser(String username) throws ServicesException;

    public List<Need> loadNeeds() throws ServicesException;

    public Need loadNeed(int needId) throws ServicesException;

    public List<Category> loadActiveCategories(boolean status) throws ServicesException;

    public List<Offer> loadOffers() throws ServicesException;

}
