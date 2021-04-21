package edu.eci.cvds.persistence.mybatisimpl.mappers;

import edu.eci.cvds.entities.Need;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NeedMapper {

    public void addNeed(@Param("need") Need need);

    public void modify(@Param("need") Need need);

    public List<Need> loadNeeds();
}
