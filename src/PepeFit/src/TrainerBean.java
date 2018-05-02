import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class TrainerBean {

    String firstName, lastName, eMail, phoneNumber, address, idNumber, gender;
    String birthDate, registirationDate;

    String courseTime;
    String courseCapacity;

    public String getBirthDate() {
        return birthDate;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseCapacity() {
        return courseCapacity;
    }



    public String getCourseTime() {
        return courseTime;
    }


    public void setCourseCapacity(String courseCapacity) {
        this.courseCapacity = courseCapacity;
    }


    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getRegistirationDate() {
        return registirationDate;
    }

    public void setRegistirationDate(String registirationDate) {
        this.registirationDate = registirationDate;
    }

    private static Map<String, Object> genders = new LinkedHashMap<String, Object>();

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


    public Map<String, Object> fillGender() {
        return genders;
    }

    static {
        genders.put("Male", "Male");
        genders.put("Female", "Female");
        genders.put("NotSpecified", "NotSpecified");
    }

    public void editActionGender(String gender) {
        if (gender.equals("Male")) {
            setGender("Male");
        } else if (gender.equals("Female")) {
            setGender("Female");
        } else if (gender.endsWith("NotSpecified")) {
            setGender("NotSpecified");
        }
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

    public void setNull() {
        this.setIdNumber(null);
        this.setFirstName(null);
        this.setAddress(null);
        this.setLastName(null);
        this.setGender(null);
        this.setPhoneNumber(null);
        this.seteMail(null);

    }


    public String addTrainerDB() throws SQLException {
        String ret;
        try {
            DatabaseBean database = new DatabaseBean();
            ArrayList<LinkedHashMap<String, Object>> results = database.execute_fetch_all("Select * from Trainer where T_ID=?", -1, this.idNumber);

            if (results.size() != 0) {
                ret = "This Trainer has been already registered!";
                database.destruct_connection();
                System.out.println(ret + "\n");
                return ret;
            }

            database.execute("Insert into Trainer values(?,?,?,?,?,\"1997-01-01\",?,?,\"1997-01-01\")", 1, this.idNumber, this.firstName, this.lastName, this.gender, this.phoneNumber, this.eMail, this.address);
            // Otherwise continue to work.
            database.commit_trans();

            database.destruct_connection();
        } catch (SQLException e) {
            System.out.println("ERROR OCCURED WHILE ADDING Trainer " + e.getMessage() + "\n");

        }

        ret = "Successfully added Trainer with ID NUMBER: " + this.idNumber + " !";
        setNull();
        System.out.println(ret + "\n");

        return ret;


    }

    public void updateTrainerDB() {
        printAll();
        try {
            DatabaseBean database = new DatabaseBean();
            database.execute("UPDATE Trainer SET t_name=?,t_surname=?,t_gender=?,t_phone=?,t_bDate=?,t_email=?,t_adress=?,t_rDate=? where T_ID=?", 1,
                    this.firstName, this.lastName, this.gender, this.phoneNumber, "1997-01-01", this.eMail, this.address, "1997-01-01", this.idNumber);
            database.commit_trans();
            database.destruct_connection();
        } catch (SQLException e) {
            System.out.println("ERROR OCCURED WHILE UPDATING TRAINER " + e.getMessage());
        }
    }

    public String resultShow = null;

    public String showTrainerDB() {

        if (this.idNumber != null) {
            System.out.println(this.idNumber + " YESSSSS");
            try {
                // Connecting to database
                DatabaseBean database = new DatabaseBean();
                // Check id from database.
                ArrayList<LinkedHashMap<String, Object>> results = database.execute_fetch_all("Select * from Trainer where T_ID=?", -1, this.idNumber);
                // If it's not in our database
                if (results.size() == 0) {
                    System.out.println("THERE IS NO PERSON WITH ID : " + this.idNumber);
                    database.destruct_connection();
                    return resultShow = "There is no person with ID : " + this.idNumber;

                } else {
                    // If it's in our database, show the trainer
                    setFirstName(results.get(0).get("T_NAME").toString());
                    setLastName(results.get(0).get("T_SURNAME").toString());
                    setGender(results.get(0).get("T_GENDER").toString());
                    setPhoneNumber(results.get(0).get("T_PHONE").toString());
                    setBirthDate(results.get(0).get("T_BDATE").toString());
                    seteMail(results.get(0).get("T_EMAIL").toString());
                    setAddress(results.get(0).get("T_ADRESS").toString());
                    setRegistirationDate(results.get(0).get("T_RDATE").toString());
                    resultShow = null;
                    database.destruct_connection();
                    return "SHOWING..";
                }

            } catch (SQLException e) {
                System.out.println("ERROR OCCURED WHILE SHOWING TRAINER " + e.getMessage());

            }
        } else {
            // If ID NUMBER place is empty.
            System.out.println("ID NUMBER IS NULL IN SHOW NUMBER");

        }


        return "ID NUMBER PLACE CANNOT BE EMPTY !";


    }


	public String addCourse(int courseId) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String courseDate = (String)dtf.format(LocalDateTime.now());
        String trainerID = "3";
    	try {
			DatabaseBean database = new DatabaseBean();
//            database.execute("CALL insert_course(?,?,?)", 1,courseName.toUpperCase(),courseTime,courseDate);
			// Checking trainer is already add that course with the same time and the same date into database !
			ArrayList<LinkedHashMap<String,Object>> result = database.execute_fetch_all("SELECT * FROM GeneralSchedule WHERE C_ID = ? AND C_TIME = ? AND C_DATE = ? AND T_ID = ?",-1,courseId,this.courseTime,courseDate,trainerID);
			// If result is empty, it means that we are good to go.
			if(result.size() == 0){
				database.execute("CALL insert_courseSchedule(?,?,?,?,?)",1,courseId,courseTime,courseDate,trainerID,this.courseCapacity);
				database.commit_trans();
				System.out.println("SUCCESSFULLY ADDED INTO GENERALSCHEDULE!" + result + "\n");
				database.destruct_connection();

                this.courseCapacity = null;
                this.courseTime = null;

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
        while (x < len) {
            System.out.print("CourseId: " + courses.courses.get(x).getCourseId() + " CourseName: " + courses.courses.get(x).getCourseName() + " Description: " + courses.courses.get(x).getCourseDescription() + "\n");
            x++;
        }


    }

    public void denemeTest() {
        System.out.print(this.courseTime + "\n");
        System.out.print(this.courseCapacity + "\n");
    }

}