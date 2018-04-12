

import org.primefaces.context.RequestContext;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.sound.midi.SysexMessage;
import javax.xml.stream.events.StartDocument;

@ManagedBean
@RequestScoped
public class MemberBean {

	private String firstName, lastName, eMail, phoneNumber, address, idNumber, gender;
	private Date birthDate,registirationDate;
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

	public Date getRegistirationDate(){
		return this.registirationDate;
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

	public void setRegistirationDate(Date registirationDate){
		this.registirationDate = registirationDate;
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
		System.out.println(this.idNumber);
		System.out.println(this.firstName);
		System.out.println(this.lastName);
		System.out.println(this.eMail);
		System.out.println(this.phoneNumber);
		System.out.println(this.address);
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

	public String resultShow = null;

	public String getResult(){
		return resultShow;
	}

	public void showMemberDB() {

		if(this.idNumber != null){
			System.out.println(this.idNumber + " YESSSSS");
			try {
				DatabaseBean database = new DatabaseBean();
				ArrayList<LinkedHashMap<String, Object>> results = database.execute_fetch_all("Select * from Member where tc=?",-1,this.idNumber);
				if(results.size()==0){
					System.out.println("THERE IS NO PERSON WITH ID : "+ this.idNumber);
					resultShow = "There is no person with ID : "+ this.idNumber;

				}else{
					setFirstName(results.get(0).get("NAME").toString());
					setLastName(results.get(0).get("SURNAME").toString());
					setGender(results.get(0).get("GENDER").toString());
					setPhoneNumber(results.get(0).get("PHONE").toString());
					setBirthDate((Date)results.get(0).get("BDATE"));
					seteMail(results.get(0).get("EMAIL").toString());
					setAddress(results.get(0).get("ADRESS").toString());
					setRegistirationDate((Date)results.get(0).get("RDATE"));
					resultShow = null;
				}
				database.destruct_connection();

			} catch (SQLException e) {
				System.out.println("ERROR OCCURED WHILE SHOWING MEMBER " + e.getMessage());

			}
		}else{
			System.out.println("ID NUMBER IS NULL IN SHOW NUMBER");
		}





	}


	public void updateMemberDB(){

		printAll();

		try{
				DatabaseBean database = new DatabaseBean();
				database.execute("UPDATE Member SET name=?,surname=?,gender=?,phone=?,bDate=?,email=?,adress=?,rDate=? where tc=?",1,
						this.firstName,this.lastName,this.gender,this.phoneNumber,"1997-01-01",this.eMail,this.address,"1997-01-01",this.idNumber);
			}catch(SQLException e){
				System.out.println("ERROR OCCURED WHILE UPDATING MEMBER " + e.getMessage());
			}
	}

	/**
	 * 	KENDIME NOT. SONRADAN KONTROL ET EĞER DATANASE != NULL DEĞİLSE OLUŞTURMA
	 */

	public void deleteMemberDB(){
		try{
			DatabaseBean database = new DatabaseBean();
			database.execute("DELETE FROM Member WHERE TC=?",1,this.idNumber);
			database.destruct_connection();
		}catch(SQLException e){
			System.out.println("ERROR OCCURED WHILE DELETING MEMBER " + e.getMessage());
		}
	}
}
