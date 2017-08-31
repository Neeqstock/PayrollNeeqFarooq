package view;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import control.AccountControl;

@SessionScoped
@ManagedBean
@Named("loginBean")
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -173093910133825075L;
	
	@Inject
	AccountControl accountControl;
	
	String username;
	String password;

	@PostConstruct
	public void init() {}

	/**
	 * passes data to the controller
	 * @return Usertype validated or null in case of error
	 */
	public String validate() {
		try {
			LoginEntity loginEntity = accountControl.validate(username, password);
		
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session;
			session = (HttpSession) context.getExternalContext().getSession(true);
			
			session.setAttribute("username", username);
			
			return loginEntity.getContractType();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String logout(){
		try {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session;
		session = (HttpSession) context.getExternalContext().getSession(true);
				
		session.invalidate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "login";
	}
	
	
	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}