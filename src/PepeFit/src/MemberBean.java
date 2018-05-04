

import org.primefaces.context.RequestContext;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.sound.midi.SysexMessage;
import javax.xml.stream.events.StartDocument;


@ManagedBean
@RequestScoped
public class MemberBean {

	private String firstName, lastName, eMail, phoneNumber, address, idNumber, gender, error, success, showError, updateSuccess, deleteSuccess;
	private String birthDate,registirationDate;
	private static Map<String,Object> genders = new LinkedHashMap<String, Object>();

	public String selectedCourse;

	public void printCourse(){
		System.out.println("Selam");
		System.out.println(this.selectedCourse);
	}


	public String getSelectedCourse() {
		return selectedCourse;
	}

	public void setSelectedCourse(String selectedCourse) {
		this.selectedCourse = selectedCourse;
	}

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

	public String getRegistirationDate(){
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public void setRegistirationDate(String registirationDate){
		this.registirationDate = registirationDate;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}
	
	public String getShowError() {
		return showError;
	}

	public void setShowError(String showError) {
		this.showError = showError;
	}
	
	public String getUpdateSuccess() {
		return updateSuccess;
	}

	public void setUpdateSuccess(String updateSuccess) {
		this.updateSuccess = updateSuccess;
	}

	public String getDeleteSuccess() {
		return deleteSuccess;
	}

	public void setDeleteSuccess(String deleteSuccess) {
		this.deleteSuccess = deleteSuccess;
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
		this.setSelectedCourse(null);
	}

	public String addMemberDB() throws SQLException {
		String ret;
		try{
			DatabaseBean database = new DatabaseBean();
			ArrayList<LinkedHashMap<String, Object>> results = database.execute_fetch_all("Select * from Member where TC=?",-1,this.idNumber);

			if(results.size() != 0){
				this.error = "This Member (ID : " + this.idNumber + ") has been already registered!";
				ret = "This User has been already registered!";
				database.destruct_connection();
				System.out.println(ret +"\n");
				return ret;
			}

			database.execute("Insert into Member values(?,?,?,?,?,\"1997-01-01\",?,?,\"1997-01-01\")", 1, this.idNumber, this.firstName, this.lastName, this.gender,this.phoneNumber,this.eMail, this.address);
			// Otherwise continue to work.
			database.commit_trans();

			database.destruct_connection();
		}catch (SQLException e){
			System.out.println("ERROR OCCURED WHILE ADDING MEMBER "+e.getMessage() + "\n");

		}

		ret = "Successfully added Member with ID NUMBER: "+ this.idNumber +" !";
		this.success = "Successfully added Member with ID NUMBER: "+ this.idNumber +" !";
		setNull();
		System.out.println(ret+"\n");

		return ret;


	}

	public String resultShow = null;

	public String getResult(){
		return resultShow;
	}

	public String showMemberDB() {

		if(this.idNumber != null){
			System.out.println(this.idNumber + " YESSSSS");
			try {
				// Connecting to database
				DatabaseBean database = new DatabaseBean();
				// Check id from database.
				ArrayList<LinkedHashMap<String, Object>> results = database.execute_fetch_all("Select * from Member where TC=?",-1,this.idNumber);
				// If it's not in our database
				if(results.size()==0){
					this.showError = "There is no Member with ID : "+ this.idNumber;
					System.out.println("THERE IS NO PERSON WITH ID : "+ this.idNumber);
					database.destruct_connection();
					return resultShow = "There is no person with ID : "+ this.idNumber;

				}else{
					// If it's in our database, show the member.
					setFirstName(results.get(0).get("NAME").toString());
					setLastName(results.get(0).get("SURNAME").toString());
					setGender(results.get(0).get("GENDER").toString());
					setPhoneNumber(results.get(0).get("PHONE").toString());
					setBirthDate((String)results.get(0).get("BDATE"));
					seteMail(results.get(0).get("EMAIL").toString());
					setAddress(results.get(0).get("ADRESS").toString());
					setRegistirationDate((String)results.get(0).get("RDATE"));
					resultShow = null;
					database.destruct_connection();
					return "SHOWING..";
				}

			} catch (SQLException e) {
				System.out.println("ERROR OCCURED WHILE SHOWING MEMBER " + e.getMessage());

			}
		}else{
			// If ID NUMBER place is empty.
			System.out.println("ID NUMBER IS NULL IN SHOW NUMBER");

		}


		return "ID NUMBER PLACE CANNOT BE EMPTY !";

	}


	public void updateMemberDB(){

		printAll();
		try{
			DatabaseBean database = new DatabaseBean();
			database.execute("UPDATE Member SET name=?,surname=?,gender=?,phone=?,bDate=?,email=?,adress=?,rDate=? where tc=?",1,
					this.firstName,this.lastName,this.gender,this.phoneNumber,"1997-01-01",this.eMail,this.address,"1997-01-01",this.idNumber);
			database.commit_trans();
			database.destruct_connection();
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
			database.commit_trans();
			database.destruct_connection();
		}catch(SQLException e){
			System.out.println("ERROR OCCURED WHILE DELETING MEMBER " + e.getMessage());
		}
	}

	public void pp(){
		if(this.selectedCourse.isEmpty()){
			System.out.println("SENIN BEN ANANIN AMINI SIKEYIM !\n" );
		}else{
			System.out.println("YERIM LEN! " + this.selectedCourse + "\n" );
		}

	}
}