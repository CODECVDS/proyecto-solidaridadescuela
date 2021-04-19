package edu.eci.cvds.persistence;

import edu.eci.cvds.entities.User;

public interface UserDAO {
    public User getUser(String username) throws PersistenceException;
}
