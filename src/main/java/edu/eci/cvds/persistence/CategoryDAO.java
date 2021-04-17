package edu.eci.cvds.persistence;

import edu.eci.cvds.entities.Category;

import java.util.List;

public interface CategoryDAO {

    public void registerCategory(Category category) throws PersistenceException;
    public void updateCategory(Category category) throws PersistenceException;
    public Category load(int categoryId) throws PersistenceException;
    public List<Category> loadAll() throws PersistenceException;
}
