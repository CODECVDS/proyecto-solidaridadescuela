package edu.eci.cvds.persistence.mybatisimpl;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.persistence.CategoryDAO;
import edu.eci.cvds.persistence.mybatisimpl.mappers.CategoryMapper;
import edu.eci.cvds.services.ServicesException;

import javax.inject.Inject;

public class MyBatisCategoryDAO implements CategoryDAO {

    @Inject
    private CategoryMapper categoryMapper;

    @Override
    public void registerCategory(Category c){
        
    }

    @Override
    public void updateCategory(String name, String description, String status) throws ServicesException {

    }

}
