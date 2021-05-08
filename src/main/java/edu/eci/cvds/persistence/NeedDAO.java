package edu.eci.cvds.persistence;

import edu.eci.cvds.entities.CountNeeds;
import edu.eci.cvds.entities.Need;

import java.util.List;

public interface NeedDAO {
    public void save(Need necesidad) throws PersistenceException;

    public void updateNeedStatus(Need need) throws PersistenceException;

    public List<Need> needs() throws PersistenceException;

    public Need load(int needId) throws PersistenceException;

    public List<CountNeeds> needsbyStatus() throws PersistenceException;

    public List<Need> loadNeedsWS() throws PersistenceException;
}
