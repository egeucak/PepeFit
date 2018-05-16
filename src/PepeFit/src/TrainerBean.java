import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class TrainerBean {

    String firstName, lastName, eMail, phoneNumber, address, idNumber, gender, error, success, showError, updateSuccess, deleteSuccess;
    String birthDate, registirationDate;

    String courseTime;
    String courseCapacity;



    private HashMap<ArrayList<String>, ArrayList<String>> openedCourses;

    private ArrayList<String> selectedOpenedCourseArray = new ArrayList<String>();

    private String selectedOpenedCourse;

    public String createOpenedCoursesButtonValue(String courseId, String courseTime){
        return (courseId.concat("/").concat(courseTime));
    }

    public String getSelectedOpenedCourse() {
        return selectedOpenedCourse;
    }

    public void setSelectedOpenedCourse(String selectedOpenedCourse) {
        selectedOpenedCourseArray.add(selectedOpenedCourse);
        this.selectedOpenedCourse = selectedOpenedCourse;
    }

    public void printButtonValue(){
        System.out.println("OLDU DE LAN");
        System.out.println(this.selectedOpenedCourse);
        System.out.println(this.selectedOpenedCourseArray);
    }

    public ArrayList<String> getSelectedOpenedCourseArray() {
        return selectedOpenedCourseArray;
    }

    public void setSelectedOpenedCourseArray(ArrayList<String> selectedOpenedCourseArray) {
        this.selectedOpenedCourseArray = selectedOpenedCourseArray;
    }



    public HashMap<ArrayList<String>, ArrayList<String>> getOpenedCourses() {
        return openedCourses;
    }

    public void setOpenedCourses(HashMap<ArrayList<String>, ArrayList<String>> openedCourses) {
        this.openedCourses = openedCourses;
    }


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
                this.error = "This Trainer (ID : " + this.idNumber + ") has been already registered!";
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
        this.success = "Successfully added Trainer with ID NUMBER: " + this.idNumber + " !";
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
            this.updateSuccess = this.idNumber + " successfully updated !";
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
                    this.showError = "There is no Trainer with ID : "+ this.idNumber;
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

    String courseDate = (String)DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now());

    public String addCourse(int courseId,String trainerID) {
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
                database.destruct_connection();
                this.success = "Course has been added succesfully!";
                return "Successfully Added !";

            }else{
                database.destruct_connection();
                System.out.println("TRAINER: " + trainerID + " ALREADY OPEN THIS CLASS WITH THE SAME TIME AND DATE!\n");
                this.error = "TRAINER: " + trainerID + " ALREADY OPEN THIS CLASS WITH THE SAME TIME AND DATE!";
                return "TRAINER: " + trainerID + " ALREADY OPEN THIS CLASS WITH THE SAME TIME AND DATE!";

            }


        } catch (SQLException e) {
            System.out.println("ERROR OCCURED WHILE ADDING COURSE " + e.getMessage());
            return "Cannot do this operation please try again later !";

        }

    }

    public String deleteCourse(int courseId, String courseTime, String trainerID) {


        try {
            DatabaseBean database = new DatabaseBean();
            database.execute("DELETE FROM GeneralSchedule WHERE C_ID=? AND C_TIME = ? AND C_DATE=? AND T_ID=?", 1, courseId, courseTime, courseDate, trainerID);
            database.commit_trans();
            System.out.println("SUCCESSFULLY DELETED FROM GENERALSCHEDULE!\n");
            database.destruct_connection();

            this.courseCapacity = null;
            this.courseTime = null;
            database.destruct_connection();
            this.success = "Course has been deleted succesfully!";
            database.destruct_connection();
            return "Successfully Deleted !";

        } catch (SQLException e) {
            System.out.println("ERROR OCCURED WHILE ADDING COURSE " + e.getMessage());
            return "Cannot do this operation right now, please try again later !";

        }
    }

    /***
     * ############################################################################################################### course delete,show
     *
     */

    public boolean contains(Set<ArrayList<String>> keySet, String key){
        for(ArrayList<String> unique_key:keySet){
            if(unique_key.get(0).equals(key)){
                return true;
            }
        }return false;
    }


    public HashMap<ArrayList<String>, ArrayList<String>> showOpenedCourses() {
        String trainerID = ShiroAuthenticationClass.getId();
        try {
            // Connecting to database
            DatabaseBean database = new DatabaseBean();
            ArrayList<LinkedHashMap<String, Object>> results;
            results = database.execute_fetch_all("SELECT * FROM GeneralSchedule NATURAL JOIN Trainer NATURAL JOIN Course WHERE T_ID=? AND C_DATE=? ORDER BY T_ID, C_ID", -1, trainerID, courseDate);

            HashMap<ArrayList<String>, ArrayList<String>> openedCourses = new HashMap<ArrayList<String>, ArrayList<String>>();
            if (results.size() != 0) {

                for (LinkedHashMap<String, Object> row : results) {
                    String course_name = row.get("C_NAME").toString();
                    String course_time = row.get("C_TIME").toString();
                    String courseID = row.get("C_ID").toString();

                    ArrayList<String> course_array = new ArrayList<String>();
                    course_array.add(courseID);
                    course_array.add(course_name);
                    if (contains(openedCourses.keySet(), courseID)) {
                        openedCourses.get(course_array).add(course_time);
                    } else {
                        ArrayList<String> course_times = new ArrayList<String>();
                        course_times.add(course_time);
                        openedCourses.put(course_array, course_times);
                    }

                }



            } else {
                this.success = "THERE IS NO OPENED COURSE!";
            }

            this.openedCourses = openedCourses;
            System.out.println(this.openedCourses);
            database.destruct_connection();
            return openedCourses;

        } catch (SQLException e) {
            System.out.println("ERROR OCCURED WHILE SHOWING REGISTERED COURSE " + e.getMessage());
            this.error = "ERROR OCCURED WHILE SHOWING REGISTERED COURSE " + e.getMessage() + "\n";
        }
        return new HashMap<ArrayList<String>, ArrayList<String>>();
    }


    public void deleteCourse() {
        HashMap<String, ArrayList<Trainer>> course_trainer_list = ListTrainerBean.course_trainer;
        String trainerID = ShiroAuthenticationClass.getId();

        for (int index = 0; index < selectedOpenedCourseArray.size(); index++) {
            if (!selectedOpenedCourseArray.get(index).isEmpty()) {
                ArrayList<String> selected_as_array = new ArrayList<String>(Arrays.asList(selectedOpenedCourseArray.get(index).split("/")));
                System.out.println(selected_as_array);
                String courseID = selected_as_array.get(0);
                String courseTime =selected_as_array.get(1);
                System.out.println("Course: " + courseID + " TrainerID: " + trainerID + " Time: " + courseTime + "\n");
                this.selectedOpenedCourse = null;
                deleteCourseDB(trainerID, courseID, courseTime);
                return;
            }
        }


    }

    public String deleteCourseDB(String trainerID, String courseId, String courseTime) {

        try {
            DatabaseBean database = new DatabaseBean();
            database.execute("DELETE FROM GeneralSchedule WHERE T_ID=? AND C_ID = ? AND C_TIME=? AND C_DATE=?", 1, trainerID, courseId, courseTime, courseDate);
            database.commit_trans();
            System.out.println("SUCCESSFULLY DELETED FROM GENERALSCHEDULE!\n");
            this.success = "Course has been deleted succesfully from Trainer with ID: "+trainerID;
            database.destruct_connection();
            return "Successfully Deleted !";

        } catch (SQLException e) {
            System.out.println("ERROR OCCURED WHILE ADDING COURSE " + e.getMessage());
            return "Cannot do this operation right now, please try again later !";

        }
    }

}