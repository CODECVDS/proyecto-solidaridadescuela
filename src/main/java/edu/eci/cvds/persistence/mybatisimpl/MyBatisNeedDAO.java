package edu.eci.cvds.persistence.mybatisimpl;

import com.google.inject.Inject;
import edu.eci.cvds.entities.Need;
import edu.eci.cvds.entities.Status;
import edu.eci.cvds.persistence.NeedDAO;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.mybatisimpl.mappers.NeedMapper;

import java.util.List;

public class MyBatisNeedDAO implements NeedDAO {

    @Inject
    private NeedMapper needMapper;

    @Override
    public void save(Need necesidad) throws PersistenceException {
        try{
            needMapper.addNeed(necesidad);
        }catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al registrar la necesidad ",e);
        }
    }

    @Override
    public void updateNeedStatus(Need need) throws PersistenceException{
        try{
            needMapper.modify(need);
        }catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar necesidades",e);
        }
    }

    @Override
    public List<Need> needs() throws  PersistenceException{
        try{
            return needMapper.loadNeeds();
        }catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar necesidades",e);
        }
    }

    @Override
    public Need load(int needId) throws  PersistenceException{
        try {
            return needMapper.load(needId);
        }catch (org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar la necesidad",e);
        }
    }

}
