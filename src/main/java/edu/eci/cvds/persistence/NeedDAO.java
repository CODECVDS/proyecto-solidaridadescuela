package edu.eci.cvds.persistence;

import edu.eci.cvds.entities.Need;

public interface NeedDAO {
    public void save(Need necesidad) throws PersistenceException;
}
