import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

@ManagedBean
@RequestScoped
public class ShiroAuthenticationClass {

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void authenticateTheUser(){

        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password); /* TODO use Sha256Hash when hiding the password later */

         try{
                currentUser.login(token);
        } catch (UnknownAccountException uae ) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed 1", "username wrong"));
        } catch (IncorrectCredentialsException ice ) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed 2", "password wrong"));
        } catch (LockedAccountException lae ) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed 3", "username locked"));
        } catch(AuthenticationException aex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed 4", aex.toString()));
            System.err.println("\n" + aex.toString());
        }
    }

    public String logout()
    {

        Subject currentUser = SecurityUtils.getSubject();

        try 
        {
            currentUser.logout();
        } 
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        
        return "/index.xhtml?faces-redirect=true";
    }
    
    public void isAnyUserLoggedIn()
    {
    	Subject currentUser = SecurityUtils.getSubject();

        if(SecurityUtils.getSubject().getPrincipal()!=null)
        {
        	if(currentUser.hasRole("admin")) {
                NavigationHandler nh=FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
                nh.handleNavigation(FacesContext.getCurrentInstance(), null, "/admin/admin.xhtml?faces-redirect=true");
        	}
        	else if(currentUser.hasRole("member")) {
                NavigationHandler nh=FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
                nh.handleNavigation(FacesContext.getCurrentInstance(), null, "/member/member.xhtml?faces-redirect=true");
        	}
        	else if(currentUser.hasRole("trainer")) {
                NavigationHandler nh=FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
                nh.handleNavigation(FacesContext.getCurrentInstance(), null, "/trainer/trainer.xhtml?faces-redirect=true");
        	}

        }
    }
}