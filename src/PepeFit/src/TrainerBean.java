import java.sql.Date;
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


    public void addCourse(String courseName, String courseTime,String courseDate,String trainerID) {
        try {
            DatabaseBean database = new DatabaseBean();
            database.execute("CALL insert_course(?,?,?)", 1,courseName.toUpperCase(),courseTime,courseDate);
            database.execute("INSERT INTO GeneralSchedule VALUES(?,?,?,?)",1,courseName.toUpperCase(),courseTime,courseDate,trainerID);
            database.commit_trans();
            database.destruct_connection();
        } catch (SQLException e) {
            System.out.println("ERROR OCCURED WHILE ADDING COURSE " + e.getMessage());
        }
    }

    public void deneme() {

        Courses courses = new Courses();
        courses.loadCourses();
        int len = courses.courses.size();
        int x = 0;
        while(x < len){
            System.out.print(courses.courses.get(x++).getCourseName() + "\n");
        }


    }
	
}
