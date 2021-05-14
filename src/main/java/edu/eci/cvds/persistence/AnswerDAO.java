package edu.eci.cvds.persistence;

import edu.eci.cvds.entities.Answer;
import edu.eci.cvds.entities.ReportAnswer;

import java.util.List;

public interface AnswerDAO {
    public void save(Answer answer) throws PersistenceException;

    public List<Answer> answers() throws PersistenceException;

    public Answer load(int answerId) throws PersistenceException;

    public List<ReportAnswer> loadReportAnswer() throws PersistenceException;

}

