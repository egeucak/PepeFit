import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class Member extends Person {

//	private int [] Achievements;
//
//
//	Progress progress = new Progress();
//
//
//	public Progress  getProgress() {
//
//		return progress;
//	}
//
//	public void setProgress(Progress progress) {
//
//		this.progress=progress;
//	}
//
//
//	public void loadStudent(String userName) {
//
//
//	}
//
//	public int [] getAchievements() {
//		return Achievements;
//	}
//
//	public void setAchievements(int[] achievements) {
//		Achievements = achievements;
//	}


	private String firstName, lastName, eMail, phoneNumber, address, idNumber, gender;
	private Date birthDate,registirationDate;


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
	
	
}
