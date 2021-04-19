package edu.eci.cvds.persistence.mybatisimpl.mappers;

import edu.eci.cvds.entities.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    public User loadUser(@Param("username") String username);
}
