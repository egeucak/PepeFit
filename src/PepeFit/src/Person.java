import java.sql.Date;

public class Person {
	
	protected String email;
	protected String name ;
	protected String phone ;
	protected Date bDate;
	
	public String  getEmail() {
		return email;
	}
	
	public String getName() {
		return name;
		
	}
	
	public Date getBDate() {
		return bDate;
	}
	
	public String getPhone() {
		return phone;
	}
	
	
	public void setEmail(String email) {
		this.email=email;
	}
	
	public void setName(String name ) {
		this.name=name;
	}
	
	public void setPhone (String phone) {
		this.phone=phone;
		
	}
	
	public void setBDate(Date bDate) {
		this.bDate=bDate;
	}
}
