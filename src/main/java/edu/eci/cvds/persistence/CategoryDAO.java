package edu.eci.cvds.persistence;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.services.ServicesException;

public interface CategoryDAO {

    public void registerCategory(Category c) throws PersistenceException;
    public void updateCategory(String name, String description, boolean status) throws PersistenceException;

}
