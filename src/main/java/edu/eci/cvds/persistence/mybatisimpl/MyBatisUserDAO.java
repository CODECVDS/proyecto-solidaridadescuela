package edu.eci.cvds.persistence.mybatisimpl;

import edu.eci.cvds.entities.User;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.UserDAO;
import edu.eci.cvds.persistence.mybatisimpl.mappers.UserMapper;

import javax.inject.Inject;

public class MyBatisUserDAO implements UserDAO {
    @Inject
    private UserMapper userMapper;
    @Override
    public User getUser(String username) throws PersistenceException {
        try {
            return userMapper.loadUser(username);
        } catch (org.apache.ibatis.exceptions.PersistenceException ex){
            throw new PersistenceException("Error al consultar el usuario",ex);

        }
    }
}
