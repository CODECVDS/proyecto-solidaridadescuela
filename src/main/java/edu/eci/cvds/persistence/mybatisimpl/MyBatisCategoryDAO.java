package edu.eci.cvds.persistence.mybatisimpl;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.persistence.CategoryDAO;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.mybatisimpl.mappers.CategoryMapper;

import javax.inject.Inject;

public class MyBatisCategoryDAO implements CategoryDAO {

    @Inject
    private CategoryMapper categoryMapper;

    @Override
    public void registerCategory(Category c) throws PersistenceException{
        try {
            categoryMapper.addCategory(c.getId(),c.getName(),c.getDescription(),c.getCreationDate(),c.getStatus(),c.getModificationDate());
        }catch (org.apache.ibatis.exceptions.PersistenceException e){
            e.printStackTrace();
            //throw new PersistenceException("Error al registrar categoria",e);
        }
    }

    @Override
    public void updateCategory(Category category) throws PersistenceException {
        try {
            categoryMapper.modifyCategory(category);
        }catch (org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al actualizar la categoria",e);
        }
    }

    @Override
    public Category load(int categoryId) throws PersistenceException {
        try {
            return categoryMapper.loadC(categoryId);
        }catch (org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar la categoria",e);
        }
    }

}
