
import org.primefaces.context.RequestContext;

import java.sql.Array;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.annotation.PostConstruct;
import javax.sound.midi.SysexMessage;
import javax.xml.stream.events.StartDocument;


@ManagedBean
@RequestScoped
public class MemberBean {


    private String firstName, lastName, eMail, phoneNumber, address, idNumber, gender, error, success, showError, updateSuccess, deleteSuccess;
    private String birthDate,registirationDate;
    private static Map<String,Object> genders = new LinkedHashMap<String, Object>();


    private ArrayList<String> selectedCourseArray = new ArrayList<String>();
    private String selectedCourse;
    /* Deregister Part */
    private ArrayList<String> selectedDeregisterCourseArray = new ArrayList<String>();
    private HashMap<ArrayList<String>,HashMap<ArrayList<String>,ArrayList<String>>> registeredCourses;
    private String selectedDeregisterCourse;

    public ArrayList<String> getSelectedCourseArray() {
        return selectedCourseArray;
    }

    public void setSelectedCourseArray(ArrayList<String> selectedCourseArray) {
        this.selectedCourseArray = selectedCourseArray;
    }

    public ArrayList<String> getSelectedDeregisterCourseArray() {
        return selectedDeregisterCourseArray;
    }

    public void setSelectedDeregisterCourseArray(ArrayList<String> selectedDeregisterCourseArray) {
        this.selectedDeregisterCourseArray = selectedDeregisterCourseArray;
    }


    public void printCourse(){
        try{
            System.out.println("Selam1");
            System.out.println(this.selectedCourse);
            System.out.println(this.selectedCourseArray);
            System.out.println(ShiroAuthenticationClass.getId());
        } catch(Exception e){
            System.out.println("Selam2");
        }
    }



    public HashMap<ArrayList<String>,HashMap<ArrayList<String>,ArrayList<String>>> getRegisteredCourses(){
        return this.registeredCourses;
    }

    public void setRegisteredCourses(HashMap<ArrayList<String>,HashMap<ArrayList<String>,ArrayList<String>>> registeredCourses){
        this.registeredCourses = registeredCourses;
    }

    public String createRegisteredCoursesButtonValue(String trainerId,String courseId, String courseTime){
        return (trainerId.concat("/").concat(courseId).concat("/").concat(courseTime));
    }



    public void setSelectedDeregisterCourse(String selectedDeregisterCourse){
        selectedDeregisterCourseArray.add(selectedDeregisterCourse);
        this.selectedDeregisterCourse = selectedDeregisterCourse;
    }

    public String getSelectedDeregisterCourse(){
        return this.selectedDeregisterCourse;
    }

    public void printButtonValue(){
        System.out.println("OLDU DE LAN");
        System.out.println(this.selectedDeregisterCourse);
        System.out.println(this.selectedDeregisterCourseArray);
    }

    public String getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(String selectedCourse) {
        selectedCourseArray.add(selectedCourse);
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
                this.error = "The Member with ID Number " + this.idNumber + " has already been registered!";
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
        this.success = "The Member with ID Number: "+ this.idNumber +" has been added successfully!";
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
                    this.showError = "There is no Member with ID Number : "+ this.idNumber;
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
            this.updateSuccess = "The Member with ID Number: " + this.idNumber + " has been updated successfully!";
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
            this.deleteSuccess= "The User with " + this.idNumber + " has been deleted successfully!";
        }catch(SQLException e){
            System.out.println("ERROR OCCURED WHILE DELETING MEMBER " + e.getMessage());
        }
    }


    /**
     *  ############################################################################################################## BUNDAN SONRASI KURSLARLA İLGİLİ
     */


    String courseDate = (String)DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now());

    public boolean contains(Set<ArrayList<String>> keySet,String key){
        for(ArrayList<String> unique_key:keySet){
            if(unique_key.get(0).equals(key)){
                return true;
            }
        }return false;
    }

    public void registerIntoCourse(String courseId) {
        HashMap<String, ArrayList<Trainer>> course_trainer_list = ListTrainerBean.course_trainer;
        String memberID = ShiroAuthenticationClass.getId();

        String selectedCourseTime = null;
        System.out.println("ZAAAAAA\n");
        System.out.println(selectedCourseArray);
        for (int index = 0; index < selectedCourseArray.size(); index++) {
            if (!selectedCourseArray.get(index).isEmpty()) {
                selectedCourseTime = selectedCourseArray.get(index);
                String trainerName = course_trainer_list.get(courseId).get(index).getTrainerName();
                String trainerID = course_trainer_list.get(courseId).get(index).getTrainerId();
                System.out.println("Course: " + courseId + " TrainerName: " + trainerName + " TrainerID: " + trainerID + " Time: " + selectedCourseTime + "\n");
                this.selectedCourse = null;
                registerCourseDB(memberID, courseId, selectedCourseTime, trainerID, trainerName);
                return;
            }
        }


    }



    public void registerCourseDB(String memberID, String courseId, String courseTime, String trainerID, String trainerName){


        try {
            // Connecting to database
            DatabaseBean database = new DatabaseBean();
            // Check id from database.
            ArrayList<LinkedHashMap<String, Object>> results = database.execute_fetch_all("Select CAPACITY from GeneralSchedule WHERE C_ID=? AND C_TIME = ? AND C_DATE=? AND T_ID=?", -1, courseId, courseTime, courseDate, trainerID);
            int capacity = (Integer) results.get(0).get("CAPACITY");
            System.out.println("CAPACITY: "+capacity + "\n");
            // If it's not in our database
            if (capacity>0) {
                /**
                 * Looking for conflicts
                 */
                int conflict = 0;
                String conflictTime = "";
                results = database.execute_fetch_all("Select * from MemberSchedule WHERE TC=? AND C_DATE=?", -1, memberID,courseDate);
                for(LinkedHashMap<String,Object> row:results){
                    List<String> rowCourseSE = Arrays.asList(row.get("C_TIME").toString().split("-"));
                    List<String> selectedCourseSE = Arrays.asList(courseTime.split("-"));
                    System.out.println(rowCourseSE);
                    System.out.println(selectedCourseSE);
                    System.out.println(row);
                    // Seçilen kursun diğer kurslarla çakışması
                    if(selectedCourseSE.get(0).compareTo(rowCourseSE.get(0)) >= 0 && selectedCourseSE.get(0).compareTo(rowCourseSE.get(1)) < 0){
                        conflict++;

                    }
                    if(selectedCourseSE.get(1).compareTo(rowCourseSE.get(0)) > 0 && selectedCourseSE.get(1).compareTo(rowCourseSE.get(1)) <= 0){
                        conflict++;
                    }

                    if(selectedCourseSE.get(0).compareTo(rowCourseSE.get(0)) == 0 && selectedCourseSE.get(1).compareTo(rowCourseSE.get(1)) == 0){
                        conflict++;
                    }

                    if(conflict>0){
                        conflictTime = row.get("C_TIME").toString();
                        break;
                    }
                }

                /**
                 * If there in no conflict
                 */
                if(conflict == 0){
                    database.execute("INSERT INTO MemberSchedule Values(?,?,?,?,?)",1, memberID, trainerID,courseId, courseTime, courseDate);
                    database.execute_fetch_all("UPDATE GeneralSchedule SET CAPACITY=CAPACITY-1 WHERE C_ID=? AND C_TIME = ? AND C_DATE=? AND T_ID=?",1, courseId, courseTime, courseDate, trainerID);
                    database.commit_trans();
                    System.out.println("SUCCESSFULLY ADDED INTO MEMBERSCHEDULE! Member: "+ memberID + "\n");
                    database.destruct_connection();
                    this.success = "Successfully registered for Trainer "+ trainerName +"\'s course at " + courseTime;

                }else{
                    // Look for it already registered or not
                    this.error =  "The selected course at " + courseTime +" conflicts with the registered course at " + conflictTime;
                    database.destruct_connection();

                }

            } else {
                database.destruct_connection();
                this.error = "There is no more place in "+ trainerName + "'s course with Course Number : "+ courseId + "!";
                System.out.println(this.error + "\n");


            }

        } catch (SQLException e) {
            System.out.println("ERROR OCCURED WHILE REGISTERING COURSE " + e.getMessage());
            this.error =  "ERROR OCCURED WHILE REGISTERING FOR COURSE " + e.getMessage()+"\n";

        }


    }

    public void dropCourse() {
        HashMap<String, ArrayList<Trainer>> course_trainer_list = ListTrainerBean.course_trainer;
        String memberID = ShiroAuthenticationClass.getId();

        for (int index = 0; index < selectedDeregisterCourseArray.size(); index++) {
            if (!selectedDeregisterCourseArray.get(index).isEmpty()) {
                ArrayList<String> selected_as_array = new ArrayList<String>(Arrays.asList(selectedDeregisterCourseArray.get(index).split("/")));
                System.out.println(selected_as_array);
                String trainerID = selected_as_array.get(0);
                String courseID = selected_as_array.get(1);
                String courseTime =selected_as_array.get(2);
                System.out.println("Course: " + courseID + " TrainerID: " + trainerID + " Time: " + courseTime + "\n");
                this.selectedDeregisterCourse = null;
                dropCourseDB(memberID, trainerID, courseID, courseTime);
                return;
            }
        }


    }

    public String dropCourseDB(String memberID, String trainerID, String courseId, String courseTime) {

        try {
            DatabaseBean database = new DatabaseBean();
            database.execute("DELETE FROM MemberSchedule WHERE TC=? AND T_ID=? AND C_ID = ? AND C_TIME=? AND C_DATE=?", 1, memberID, trainerID, courseId, courseTime, courseDate);
            database.execute("UPDATE GeneralSchedule SET CAPACITY=CAPACITY+1 WHERE C_ID=? AND T_ID=? AND C_TIME = ? AND C_DATE=?", 1, courseId, trainerID,courseTime, courseDate);
            database.commit_trans();
            System.out.println("SUCCESSFULLY DROPPED FROM MEMBERSCHEDULE!\n");
            this.success = "The course has been deleted succesfully from the Member with ID Number: "+memberID;
            database.destruct_connection();
            return "Successfully Deleted !";

        } catch (SQLException e) {
            System.out.println("ERROR OCCURED WHILE ADDING COURSE " + e.getMessage());
            return "Cannot do this operation right now, please try again later !";

        }
    }

    public  HashMap<ArrayList<String>,HashMap<ArrayList<String>,ArrayList<String>>> showRegisteredCourses(){
        String memberID = ShiroAuthenticationClass.getId();
        try {
            // Connecting to database
            DatabaseBean database = new DatabaseBean();
            ArrayList<LinkedHashMap<String, Object>> results;
            results = database.execute_fetch_all("SELECT * FROM MemberSchedule NATURAL JOIN Trainer NATURAL JOIN Course WHERE TC=? AND C_DATE=? ORDER BY T_ID, C_ID",-1,memberID,courseDate);
            // En dıştaki map
            HashMap<ArrayList<String>,HashMap<ArrayList<String>,ArrayList<String>>> registeredCourses = new HashMap<ArrayList<String>,HashMap<ArrayList<String>,ArrayList<String>>>();


            if(results.size() != 0){

                for(LinkedHashMap<String, Object> row : results){

                    String trainer_name = row.get("T_NAME").toString();
                    String trainerID = row.get("T_ID").toString();
                    String course_name = row.get("C_NAME").toString();
                    String course_time = row.get("C_TIME").toString();
                    String courseID = row.get("C_ID").toString();

                    ArrayList<String> trainer_array = new ArrayList<String>();
                    ArrayList<String> course_array = new ArrayList<String>();
                    trainer_array.add(trainerID);trainer_array.add(trainer_name);
                    course_array.add(courseID);course_array.add(course_name);

                    // Eğer trainer eklenmemişse

                    if(contains(registeredCourses.keySet(),trainerID)){
                        // Eğer courseta trainerda varsa
                        if(contains(registeredCourses.get(trainer_array).keySet(),courseID)){
                            registeredCourses.get(trainer_array).get(course_array).add(course_time);

                        }// Eğer course ismi eklenmemişse
                        else{
                            ArrayList<String> course_times = new ArrayList<String>();
                            course_times.add(course_time);
                            registeredCourses.get(trainer_array).put(course_array,course_times);
                        }
                    }else{
                        // En içteki map
                        HashMap<ArrayList<String>, ArrayList<String>> course_name_and_course = new HashMap<ArrayList<String>, ArrayList<String>>();
                        ArrayList<String> course_times = new ArrayList<String>();
                        course_times.add(row.get("C_TIME").toString());
                        course_name_and_course.put(course_array, course_times);
                        // Map:TrainerName(Map:Coursename(ArrayList))
                        registeredCourses.put(trainer_array, course_name_and_course);

                    }

                }


                System.out.println(registeredCourses);

            }else{
                this.error = "THERE IS NO REGISTERED COURSE!";
            }
            this.registeredCourses = registeredCourses;
            System.out.println("Show çalıştı");
            return registeredCourses;

        } catch(SQLException e) {
            System.out.println("ERROR OCCURED WHILE SHOWING REGISTERED COURSE " + e.getMessage());
            this.error =  "ERROR OCCURED WHILE SHOWING REGISTERED COURSES " + e.getMessage()+"\n";
        }
        return new HashMap<ArrayList<String>,HashMap<ArrayList<String>,ArrayList<String>>>();
    }
}