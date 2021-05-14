package edu.eci.cvds.persistence.mybatisimpl;

import com.google.inject.Inject;
import edu.eci.cvds.entities.Answer;
import edu.eci.cvds.persistence.AnswerDAO;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.mybatisimpl.mappers.AnswerMapper;

import java.util.List;

public class MyBatisAnswerDAO implements AnswerDAO {
    @Inject
    private AnswerMapper answerMapper;


    @Override
    public void save(Answer answer) throws PersistenceException {
        try{
            answerMapper.addAnswer(answer);
        }catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al registar una respuesta",e);
        }
    }

    @Override
    public List<Answer> answers() throws PersistenceException {
        try{
            return answerMapper.answers();
        }catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al cargar las respuestas",e);
        }
    }

    @Override
    public Answer load(int answerId) throws PersistenceException {
        try{
            return answerMapper.loadAnswer(answerId);
        }catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al cargar las respuestas",e);
        }
    }
}
