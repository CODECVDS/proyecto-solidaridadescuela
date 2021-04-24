package edu.eci.cvds.managedbeans;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;

@SessionScoped
@ManagedBean(name="userBean")
public class UserBean implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(UserBean.class);
    private String username;
    private String userpassword;
    private String redirectURL="/faces/signin.xhtml";
    private Subject currentUser;
    private String path;
    private boolean rememberMe=false;
    public void signin(){
        UsernamePasswordToken token = new UsernamePasswordToken(getUsername(),new Sha256Hash(getUserpassword()).toHex());
        currentUser = SecurityUtils.getSubject();

        try {
            currentUser.login(token);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            Session session = currentUser.getSession();
            session.setAttribute("username",username);
            session.setAttribute("currentUser",currentUser);
            if(currentUser.hasRole("Administrator")){
                setPath("homeA");
                facesContext.getExternalContext().redirect("/faces/homeA.xhtml");
            }else if(currentUser.hasRole("Student")){
                setPath("homeB");
                facesContext.getExternalContext().redirect("/faces/homeB.xhtml");
            }
        } catch ( UnknownAccountException e ) {
            //username wasn't in the system, show them an error message?
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sign in Error", "Incorrect Credentials"));
            logger.error(e.getMessage(),e);
        } catch ( IncorrectCredentialsException e ) {
            //password didn't match, try again?
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sign in Error", "Incorrect Credentials"));
            logger.error(e.getMessage(),e);
        } catch ( LockedAccountException e ) {
            //account for that username is locked - can't login.  Show them a message?
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sign in Error", "Sign in Error"));
            logger.error(e.getMessage(),e);
        } catch ( AuthenticationException e ) {
            //unexpected condition - error?
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sign in Error", "Sign in Error"));
            logger.error(e.getMessage(),e);
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sign in Error", "Sign in Error"));
        }
    }

    public void signOut() {
        SecurityUtils.getSubject().logout();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(redirectURL);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getRedirectURL() {
        return redirectURL;
    }

    public void setRedirectURL(String redirectURL) {
        this.redirectURL = redirectURL;
    }

    public Subject getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Subject currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}

