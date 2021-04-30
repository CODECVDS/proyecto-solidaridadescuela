package edu.eci.cvds.persistence;

import edu.eci.cvds.entities.Answer;

import java.util.List;

public interface AnswerDAO {
    public void save(Answer answer) throws PersistenceException;

    public List<Answer> answers() throws PersistenceException;

    public Answer load(int answerId) throws PersistenceException;
}

