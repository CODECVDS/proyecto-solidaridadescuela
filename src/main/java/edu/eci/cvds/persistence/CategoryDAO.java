package edu.eci.cvds.persistence;

import edu.eci.cvds.entities.Category;

public interface CategoryDAO {

    public void registerCategory(Category c) throws PersistenceException;
    public void updateCategory(Category category) throws PersistenceException;
    public Category load(int categoryId) throws PersistenceException;
}
