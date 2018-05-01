import java.sql.SQLException;

public class Trainer extends Person {
	
	
	private String bio;
	
	public String getBio()

    {
          return bio;
    }

    public void setBio(String bio)

    {
          this.bio = bio;
          
    }
	
//	public void addCourse(String courseName, String courseTime,String courseDate,int trainerID) {
//	    try {
//            DatabaseBean database = new DatabaseBean();
//            database.execute("INSERT INTO ? VALUES(?,?,?)", 1,"Course",courseName,courseTime,courseDate);
//            database.destruct_connection();
//        } catch (SQLException e) {
//            System.out.println("ERROR OCCURED WHILE ADDING COURSE " + e.getMessage());
//        }
//	}
//
	public void addAchievement(int studentId,int achievementId){
		
		
	}
	
	
	
}
