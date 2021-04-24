package edu.eci.cvds.services;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.entities.Need;
import edu.eci.cvds.entities.Offer;
import edu.eci.cvds.entities.User;

import java.util.List;

public interface SolidaridadServices {

    public void registerCategory(Category c) throws ServicesException;

    public void updateCategory(Category category) throws ServicesException;

    public void registerNeed(Need need) throws ServicesException;

    public void updateNeed(Need need) throws ServicesException;

    public void registerOffer(Offer offer) throws ServicesException;

    public Category loadCategory(int categoryId) throws ServicesException;

    public List<Category> loadCategories() throws ServicesException;

    public User getUser(String username) throws ServicesException;

    public List<Need> loadNeeds() throws ServicesException;

    public Need loadNeed(int needId) throws ServicesException;

    public List<Category> loadActiveCategories(boolean status) throws ServicesException;

}
