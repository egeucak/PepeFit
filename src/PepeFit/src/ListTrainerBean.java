import com.google.api.services.sqladmin.model.Database;
import org.primefaces.context.RequestContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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


public class ListTrainerBean {

    public String getCourseTimeElement(ArrayList<String> time_capacity, Integer indexVal){ // 0 for time, 1 for capacity, 2 for combined
        if (indexVal==2){
            return ("C: "+String.format("%02d", Integer.parseInt(time_capacity.get(1)))+" | " + time_capacity.get(0));
        }
        return time_capacity.get(indexVal);
    }

    private ArrayList<TrainerTemp> trainerNames = new ArrayList<TrainerTemp>();

    public DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    public ArrayList<Trainer> getTrainer_deneme() {
        return trainer_deneme;
    }

    public void printSelam(){
        System.out.println("Selam");
    }

    public ArrayList<ArrayList<Trainer>> coursesTimes = new ArrayList<ArrayList<Trainer>>();

    public void fillCoursesTimes(String maxId) throws SQLException {
        for (int i=1;i<=Integer.parseInt(maxId);i++){
            coursesTimes.add(loading(Integer.toString(i)));
        }
    }

    public void setCoursesTimes(ArrayList<ArrayList<Trainer>> coursesTimes){
        this.coursesTimes = coursesTimes;
    }

    public ArrayList<Trainer> getCoursesTimes(String id){
        return coursesTimes.get(Integer.parseInt(id)-1);
    }

    public void setTrainer_deneme(ArrayList<Trainer> trainer_deneme) {
        this.trainer_deneme = trainer_deneme;
    }

    public static HashMap<String, ArrayList<Trainer>> getCourse_trainer() {
        return course_trainer;
    }

    public static void setCourse_trainer(HashMap<String, ArrayList<Trainer>> course_trainer) {
        ListTrainerBean.course_trainer = course_trainer;
    }

    static  public HashMap<String,ArrayList<Trainer>> course_trainer = new HashMap<String, ArrayList<Trainer>>();




    public ArrayList<Trainer> trainer_deneme = new ArrayList<Trainer>();
    public int trainerX = -1;
    public int old_courseId;



    public ArrayList<Trainer> loading(String courseId) throws SQLException {

        if(this.trainerX == -1){
            System.out.println("FUCK1 + " + courseId+ "\n");
            this.trainerX = Integer.parseInt(courseId) - 1;
            this.trainer_deneme = (ArrayList<Trainer>) loadTrainers(courseId).clone();
            this.old_courseId = Integer.parseInt(courseId);

        }else if(trainerX == (Integer.parseInt(courseId) - 1)){
            System.out.println("FUCK2 + " + courseId  + "\n");
            return this.trainer_deneme;
        }else if(Integer.parseInt(courseId) != this.old_courseId){
            System.out.println("FUCK3 + " + courseId+ "\n");
            this.trainerX = Integer.parseInt(courseId) - 1;
            this.trainer_deneme = (ArrayList<Trainer>) loadTrainers(courseId).clone();
            this.old_courseId = Integer.parseInt(courseId);

            return this.trainer_deneme;
        }

        return this.trainer_deneme;
    }



    public ArrayList<Trainer> loadTrainers(String courseId) throws SQLException {

        String courseDate = (String)dtf.format(LocalDateTime.now());
        DatabaseBean database = new DatabaseBean();

        ArrayList<Trainer> trainers = new ArrayList<Trainer>();
        ArrayList<LinkedHashMap<String,Object>> result = null;
        ArrayList<ArrayList<Object>> trainer_times = new ArrayList<ArrayList<Object>>();
        try {


            // Fetch unique trainers for given course id and course_date.
            result = database.execute_fetch_all("SELECT DISTINCT T_ID FROM GeneralSchedule WHERE C_ID=? AND C_DATE=? ORDER BY T_ID ASC",-1,courseId,courseDate);
            // Fetch course hours for these trainers.
            for(LinkedHashMap<String,Object> row_map:result){
                String trainerId = row_map.get("T_ID").toString();
                ArrayList<LinkedHashMap<String,Object>> result_times = null;
                result_times = database.execute_fetch_all("SELECT T_NAME,C_TIME, CAPACITY FROM Trainer NATURAL JOIN GeneralSchedule WHERE C_ID=? AND T_ID=? AND C_DATE=? AND CAPACITY > 0 ORDER BY C_TIME ASC",-1,courseId,trainerId,courseDate);
                // Trainer's course times for given c_id. It will store in Trainer's object.
                // Store in an array for storing Trainer's object.

                if(result_times.size() != 0){
                    ArrayList<ArrayList<String>> times = new ArrayList<ArrayList<String>>();
                    for (LinkedHashMap<String, Object> result_time : result_times) {
                        ArrayList<String> time_and_capacity = new ArrayList<String>();
                        time_and_capacity.add(result_time.get("C_TIME").toString());
                        time_and_capacity.add(result_time.get("CAPACITY").toString());
                        times.add(time_and_capacity);
                    }
                    ArrayList<Object> names_times = new ArrayList<Object>();
                    names_times.add(trainerId); // 0
                    names_times.add(result_times.get(0).get("T_NAME"));// 1
                    names_times.add(times); // 2
                    trainer_times.add(names_times);

                }

            }

        } catch (SQLException e) {
            System.out.println("ERROR OCCURED WHILE PULLING COURSES " + e.getMessage());
        }

        if(result == null){
            System.out.println("LOAD TRAINERS RESULT LIST IS EMPTY\n");
        }else{
            int len = trainer_times.size();
            int x = 0;
            for(ArrayList<Object> times:trainer_times){
                Trainer trainer = new Trainer(times.get(0).toString(),times.get(1).toString(),(ArrayList<ArrayList<String>>) times.get(2),courseDate);
                trainers.add(trainer);
                System.out.println((ArrayList<ArrayList<String>>) times.get(2));
            }

            course_trainer.put(courseId,trainers);
        }

        database.destruct_connection();


        return trainers;
    }

    
    public ArrayList<TrainerTemp> getTrainerNames() {
        
        ArrayList<LinkedHashMap<String, Object>> trainers = null;

        try {
            DatabaseBean database = new DatabaseBean();
            trainers = database.execute_fetch_all("SELECT * FROM Trainer", -1);
            database.destruct_connection();
        } catch (SQLException e) {
            System.out.println("ERROR OCCURED WHILE PULLING COURSES " + e.getMessage());
        }

        for (LinkedHashMap<String, Object> trainer : trainers) {
            System.out.println("NAME : " + trainer.get("T_NAME").toString() + " " +
                    trainer.get("T_SURNAME").toString());
            System.out.println("MAIL : " + trainer.get("T_EMAIL"));
            System.out.println("BIO : " + trainer.get("BIO"));
            
            trainerNames.add(new TrainerTemp(trainer.get("T_NAME").toString() + " " + trainer.get("T_SURNAME").toString(),
                    trainer.get("T_EMAIL").toString(), trainer.get("BIO").toString()));
        }


    
        return trainerNames;
    }




}

