import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Courses {

	
	ArrayList<Course> courses;


	public ArrayList<Course> loadCourses() {
	    ArrayList<Course> courses = new ArrayList<Course>();

        ArrayList<LinkedHashMap<String,Object>> result = null;
        try {
            DatabaseBean database = new DatabaseBean();
            result = database.execute_fetch_all("SELECT * FROM Course",-1);
            database.destruct_connection();
        } catch (SQLException e) {
            System.out.println("ERROR OCCURED WHILE PULLING COURSES " + e.getMessage());
        }

        if(result == null){
            System.out.println("LOAD COURSES RESULT LIST IS EMPTY\n");
        }else{
            int len = result.size();
            int x = 0;
            while(x < len){
                LinkedHashMap<String,Object> row = result.get(x);
                Course course = new Course((Integer) row.get("C_ID"),row.get("C_NAME").toString(),row.get("C_DESCRIPTION").toString());
                courses.add(course);
                x++;
            }
        }
        this.courses = courses;
        return courses;
	}
	
	
	
	public void updateCourses(Course course[]) {
		
		
	}
	
	
}	

