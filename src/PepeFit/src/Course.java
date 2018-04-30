import java.util.Map;

public class Course {

    private String courseName;
    private int courseId;
    private String courseTime;
    private String courseDate;
    private Integer trainerID;
    private Map[] trainers;


    public String getCourseTime() {
        return courseTime;

    }

    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public Integer getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(Integer trainerID) {
        this.trainerID = trainerID;
    }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Map[] getTrainers() {
        return trainers;
    }

    public void setTrainers(Map[] trainers) {
        this.trainers = trainers;
    }

    Course(String courseName,String courseTime,String courseDate){
        setCourseName(courseName);
        setCourseTime(courseTime);
        setCourseDate(courseDate);
    }

}
