package com.pepefit.login;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class LoginBean {
	
	String username,password,accountType;

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

	public String getAccountType() { /* DB'den gelen account type a göre sayfa yönlendirmesi yapmasý lazým */
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public String redirect() { /* bu kýsýma sonra if statements eklenerek accountType'a göre yönlendirme yapýlacak */
		
		return "admin/admin.xhtml?faces-redirect=true";
	}
}
