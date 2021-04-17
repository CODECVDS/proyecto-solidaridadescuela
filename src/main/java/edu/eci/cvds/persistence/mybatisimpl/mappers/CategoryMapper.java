package edu.eci.cvds.persistence.mybatisimpl.mappers;

import edu.eci.cvds.entities.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {

    public void addCategory(@Param("category") Category category);

    public void modifyCategory(@Param("category") Category category);

    public Category loadC(@Param("id") int categoryId);

    public List<Category> loadAllC();
}
