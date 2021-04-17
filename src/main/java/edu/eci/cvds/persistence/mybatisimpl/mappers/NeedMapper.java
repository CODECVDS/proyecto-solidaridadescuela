package edu.eci.cvds.persistence.mybatisimpl.mappers;

import edu.eci.cvds.entities.Need;
import org.apache.ibatis.annotations.Param;

public interface NeedMapper {

    public void addNeed(@Param("need") Need need);
}
