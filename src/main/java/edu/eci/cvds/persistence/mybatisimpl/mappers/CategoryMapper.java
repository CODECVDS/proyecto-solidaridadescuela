package edu.eci.cvds.persistence.mybatisimpl.mappers;

import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface CategoryMapper {

    public void addCategory(@Param("id") int id,
                            @Param("name") String name,
                            @Param("description") String description,
                            @Param("creationDate")Date creationDate,
                            @Param("status") boolean status,
                            @Param("modificationDate")Date modificationDate);

    public void modifyCategory(@Param("id") int id,
                               @Param("name") String name,
                               @Param("description") String description,
                               @Param("status") boolean status);
}
