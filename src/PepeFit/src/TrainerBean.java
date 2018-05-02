import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class TrainerBean {

	String firstName, lastName, eMail, phoneNumber, address, idNumber, gender;
	Date birthDate;

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


	public String addCourse(int courseId, String courseTime,String courseDate,String trainerID,int capacity) {
		try {
			DatabaseBean database = new DatabaseBean();
//            database.execute("CALL insert_course(?,?,?)", 1,courseName.toUpperCase(),courseTime,courseDate);
			// Checking trainer is already add that course with the same time and the same date into database !
			ArrayList<LinkedHashMap<String,Object>> result = database.execute_fetch_all("SELECT * FROM GeneralSchedule WHERE C_ID = ? AND C_TIME = ? AND C_DATE = ? AND TRAINER_ID = ?",-1,courseId,courseTime,courseDate,trainerID);
			// If result is empty, it means that we are good to go.
			if(result.size() == 0){
				database.execute("CALL insert_courseSchedule(?,?,?,?,?)",1,courseId,courseTime,courseDate,trainerID,capacity);
				database.commit_trans();
				System.out.println("SUCCESSFULLY ADDED INTO GENERALSCHEDULE!" + result + "\n");
				database.destruct_connection();
				return "Successfully Added !";

			}else{
				database.destruct_connection();
				System.out.println("TRAINER: " + trainerID + " ALREADY OPEN THIS CLASS WITH THE SAME TIME AND DATE!\n");
				return "TRAINER: " + trainerID + " ALREADY OPEN THIS CLASS WITH THE SAME TIME AND DATE!";

			}


		} catch (SQLException e) {
			System.out.println("ERROR OCCURED WHILE ADDING COURSE " + e.getMessage());
			return "Cannot do this operation please try again later !";

		}
	}

	public void deneme() {

		Courses courses = new Courses();
		courses.loadCourses();
		int len = courses.courses.size();
		int x = 0;
		while(x < len){
			System.out.print("CourseId: "+ courses.courses.get(x).getCourseId() + " CourseName: " + courses.courses.get(x).getCourseName() + " Description: " + courses.courses.get(x).getCourseDescription() + "\n");
			x++;
		}


	}

	public void denemeTest(){
		System.out.print(this.firstName+"\n");
	}

}