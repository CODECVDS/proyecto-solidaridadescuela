package edu.eci.cvds.persistence;

import edu.eci.cvds.entities.Need;
import edu.eci.cvds.entities.Status;

import java.util.List;

public interface NeedDAO {
    public void save(Need necesidad) throws PersistenceException;

    public void updateNeedStatus(Status status) throws PersistenceException;

    public List<Need> needs() throws PersistenceException;

    public Need load(int needId) throws PersistenceException;

    public List<Need> needsbyStatus() throws PersistenceException;
}
