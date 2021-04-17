package edu.eci.cvds.persistence.mybatisimpl;

import com.google.inject.Inject;
import edu.eci.cvds.entities.Need;
import edu.eci.cvds.persistence.NeedDAO;
import edu.eci.cvds.persistence.mybatisimpl.mappers.NeedMapper;
import org.apache.ibatis.exceptions.PersistenceException;

public class MyBatisNeedDAO implements NeedDAO {

    @Inject
    private NeedMapper needMapper;

    @Override
    public void save(Need necesidad) throws PersistenceException {
        try{
            needMapper.addNeed(necesidad);
        }catch(org.apache.ibatis.exceptions.PersistenceException ex){
            ex.printStackTrace();
            //throw new PersistenceException("Error al registrar la necesidad "+necesidad.toString(),ex);
        }
    }
}
