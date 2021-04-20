package edu.eci.cvds.managedbeans;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
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
@ManagedBean(name="userBean",eager = true)
public class UserBean implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(UserBean.class);
    private String username;
    private String userpassword;
    private String redirectURL="/faces/menu.xhtml";
    Subject subject;

    public void signin(){
        //Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro.ini");
        //SecurityManager securityManager=factory.getInstance();
        //SecurityUtils.setSecurityManager(securityManager);
        subject= SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(getUsername(),new Sha256Hash(getUserpassword()).toHex());
        try {
            subject.login(token);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/menu.xhtml");

        } catch ( UnknownAccountException e ) {
            //username wasn't in the system, show them an error message?
            logger.error(e.getMessage(),e);
        } catch ( IncorrectCredentialsException e ) {
            //password didn't match, try again?
            logger.error(e.getMessage(),e);
        } catch ( LockedAccountException e ) {
            //account for that username is locked - can't login.  Show them a message?
            logger.error(e.getMessage(),e);
        } catch ( AuthenticationException e ) {
            //unexpected condition - error?
            logger.error(e.getMessage(),e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void logOut() {

        SecurityUtils.getSubject().logout();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(redirectURL);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
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
}
