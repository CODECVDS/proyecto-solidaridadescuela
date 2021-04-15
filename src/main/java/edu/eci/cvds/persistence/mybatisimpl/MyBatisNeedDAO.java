package edu.eci.cvds.persistence.mybatisimpl;

import com.google.inject.Inject;
import edu.eci.cvds.entities.Need;
import edu.eci.cvds.persistence.NeedDAO;
import edu.eci.cvds.persistence.mybatisimpl.mappers.NeedMapper;
import org.mybatis.guice.transactional.Transactional;

public class MyBatisNeedDAO implements NeedDAO {

    @Inject
    private NeedMapper needMapper;

    @Override
    @Transactional
    public void save(Need necesidad) {
        try{
            needMapper.insertarNecesidad(necesidad);
        }catch(){

        }
    }
}
