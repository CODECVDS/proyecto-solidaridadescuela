package edu.eci.cvds.services;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.entities.Need;

import java.util.List;

public interface SolidaridadServices {

    public void registerCategory(Category c) throws ServicesException;

    public void updateCategory(Category category) throws ServicesException;

    public void registerNeed(Need need) throws ServicesException;

    public Category loadCategory(int categoryId) throws ServicesException;

    public List<Category> loadCategories() throws ServicesException;
}
