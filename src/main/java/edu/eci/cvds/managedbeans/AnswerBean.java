package edu.eci.cvds.managedbeans;

import com.sun.java.swing.plaf.windows.WindowsButtonListener;
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
public class AnswerBean {

    @Inject
    private SolidaridadServices solidaridadServices;
    private Answer answer;

    private int id;
    private String name;
    private Date creationDate;
    private String comments;
    private int offer;
    private int need;

    public List<Answer> getAnswers() throws ServicesException{
        try{
            System.out.println("get"+solidaridadServices.loadAnswers().size());
            return solidaridadServices.loadAnswers();
        }catch (ServicesException e){
            throw e;
        }

    }
    public void register(){
        try {
            solidaridadServices.registerAnswer(answer);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Answer Added"));
        }catch (ServicesException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Add Error","Add Error"));
        }
        PrimeFaces.current().executeScript("PF('manageAnswerDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-answers");
    }
    public void openNew(){
        this.answer = new Answer();
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

    public int getOffer() {
        return offer;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }

    public int getNeed() {
        return need;
    }

    public void setNeed(int need) {
        this.need = need;
    }
}
