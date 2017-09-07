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
import tools.LoginEntity;

/**
 * Logic for the login.xhtml view.
 * 
 * @author neeqstock
 *
 */
@SessionScoped
@ManagedBean
@Named("loginBean")
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -173093910133825075L;

	@Inject
	AccountControl accountControl;

	private int accountID;
	private String username;
	private String password;
	private String contractType;
	private int employeeID;

	@PostConstruct
	public void init() {
		contractType = "";
	}

	public String validate() {
		try {
			LoginEntity loginEntity = accountControl.validate(username, password);
			
			if (loginEntity!=null){
				contractType = loginEntity.getContractType();
				accountID = loginEntity.getAccountID();
				employeeID = loginEntity.getEmployeeID();
				
				FacesContext context = FacesContext.getCurrentInstance();
				
				HttpSession session;
				session = (HttpSession) context.getExternalContext().getSession(true);
				session.setAttribute("employeeID", employeeID);

				System.out.println(loginEntity.getContractType()); // TEST

				if (loginEntity.isAdmin()) {
					return "Admin";
				} else {
					return loginEntity.getContractType();
				}
			}
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String logout() {
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
	
	public String continueAsAdmin(){
		return "Admin";
	}
	
	public String continueAsEmployee(){
		return contractType;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}