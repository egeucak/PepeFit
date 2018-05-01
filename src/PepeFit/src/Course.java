import java.util.Map;

public class Course {


    private int courseId;
    private String courseName;
    private String courseDescription;


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    Course(Integer courseId, String courseName, String courseDescription){
        setCourseId(courseId);
        setCourseName(courseName);
        setCourseDescription(courseDescription);
    }

}
