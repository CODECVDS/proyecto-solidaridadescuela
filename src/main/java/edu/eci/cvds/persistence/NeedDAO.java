package edu.eci.cvds.persistence;

import edu.eci.cvds.entities.CountStatus;
import edu.eci.cvds.entities.Need;

import java.util.List;

public interface NeedDAO {
    public void save(Need necesidad) throws PersistenceException;

    public void updateNeedStatus(Need need) throws PersistenceException;

    public List<Need> needs() throws PersistenceException;

    public Need load(int needId) throws PersistenceException;

    public List<CountStatus> needsbyStatus() throws PersistenceException;

    public List<Need> loadNeedsWS() throws PersistenceException;
}
