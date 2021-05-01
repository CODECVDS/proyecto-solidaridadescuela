package edu.eci.cvds.managedbeans;

import edu.eci.cvds.entities.Answer;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.SolidaridadServices;
import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@ManagedBean(name="answerBean")
@SessionScoped
public class AnswerBean extends BasePageBean {

    @Inject
    private SolidaridadServices solidaridadServices;
    private Answer answer;
    private int id;
    private String name;
    private Date creationDate;
    private String comments;
    private int answerTo;
    private int toId;

    public List<Answer> getAnswers() throws ServicesException{
        try{
            return solidaridadServices.loadAnswers();
        }catch (ServicesException e){
            throw e;
        }
    }

    public void registerNeed(int needId){
        try {
            answer.setAnswerTo("Need");
            answer.setToId(needId);
            solidaridadServices.registerAnswer(answer);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Answer Added"));
        }catch (ServicesException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Add Error","Add Error"));
        }
        PrimeFaces.current().executeScript("PF('manageAnswerDialog').hide()");
    }

    public void registerOffer(int offerId){
        try {
            answer.setAnswerTo("Offer");
            answer.setToId(offerId);
            solidaridadServices.registerAnswer(answer);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Answer Added"));
        }catch (ServicesException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Add Error","Add Error"));
        }
        PrimeFaces.current().executeScript("PF('manageAnswerDialog').hide()");
    }


    public void openNew(){
        this.answer = new Answer();
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getAnswerTo() {
        return answerTo;
    }

    public void setAnswerTo(int answerTo) {
        this.answerTo = answerTo;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }
}
