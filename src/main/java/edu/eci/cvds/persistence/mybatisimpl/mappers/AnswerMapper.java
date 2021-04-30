package edu.eci.cvds.persistence.mybatisimpl.mappers;

import edu.eci.cvds.entities.Answer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnswerMapper {
    public void addAnswer( @Param("answer") Answer answer);

    public Answer loadAnswer( @Param("id") int id);

    public List<Answer> answers();

}
