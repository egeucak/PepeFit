import java.sql.SQLException;
import java.util.ArrayList;

public class Trainer extends Person {
	
	private String trainerId;
    private String trainerName;
    private String trainerGender;


    private ArrayList<String> courseTime;

    private String courseDate;
    private String bio;

    public ArrayList<String> getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(ArrayList<String> courseTime) {
        this.courseTime = courseTime;
    }


    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public String getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(String trainerId) {
        this.trainerId = trainerId;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getTrainerGender() {
        return trainerGender;
    }

    public void setTrainerGender(String trainerGender) {
        this.trainerGender = trainerGender;
    }


    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }



    Trainer(String trainerId,String trainerName, ArrayList<String> courseTime, String courseDate){
        setTrainerId(trainerId);
        setTrainerName(trainerName);
        setCourseTime(courseTime);
        setCourseDate(courseDate);
    }

	
}