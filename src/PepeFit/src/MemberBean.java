import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class MemberBean {

	private String firstName, lastName, eMail, phoneNumber, address, idNumber, gender;
	private Date birthDate;
	private static Map<String,Object> genders = new LinkedHashMap<String, Object>();

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void editActionGender(String gender) {
		if(gender.equals("Male")) {
			setGender("Male");
		}
		else if(gender.equals("Female")) {
			setGender("Female");
		}
		else if(gender.endsWith("NotSpecified")) {
			setGender("NotSpecified");
		}
	}

	static {
		genders.put("Male", "Male");
		genders.put("Female", "Female");
		genders.put("NotSpecified", "NotSpecified");
	}

	public Map<String,Object> fillGender() {
		return genders;
	}

	public void printAll() {
		System.out.println(this.firstName);
		System.out.println(this.lastName);
		System.out.println(this.eMail);
		System.out.println(this.phoneNumber);
		System.out.println(this.address);
		System.out.println(this.idNumber);
		System.out.println(this.gender);

	}

	public void setNull(){
		this.setIdNumber(null);
		this.setFirstName(null);
		this.setAddress(null);
		this.setLastName(null);
		this.setGender(null);
		this.setPhoneNumber(null);
		this.seteMail(null);

	}

	public void addMemberDB() throws SQLException {

		try{
			DatabaseBean database = new DatabaseBean();
			database.execute("Insert into Member values(?,?,?,?,?,\"1997-01-01\",?,?,\"1997-01-01\")", 1, this.idNumber, this.firstName, this.lastName, this.gender,this.phoneNumber,this.eMail, this.address);
			database.destruct_connection();
		}catch (SQLException e){
			System.out.println("ERROR OCCURED WHILE ADDING MEMBER "+e.getMessage());
		}


		setNull();


	}

	public String updateMemberDB(){

		System.out.println("FUCKKKDSFSJDNFŞKSJM");
//	    try{
//            DatabaseBean database = new DatabaseBean();
//            ArrayList<LinkedHashMap<String,Object>> results = null;
//            if(results == null){
//                System.out.println("FUCKKK");
//            }
//        }catch(SQLException e){
//	        System.out.println("ERROR OCCURED WHILE UPDATING MEMBER "+e.getMessage());
//        }


		return "a";

	}

}
