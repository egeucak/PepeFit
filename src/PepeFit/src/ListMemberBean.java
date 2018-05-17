import org.primefaces.context.RequestContext;

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

public class ListMemberBean {

    public static ArrayList<MemberTemp> userNames = new ArrayList<MemberTemp>();
    static ArrayList<LinkedHashMap<String,Object>> members = null;
    static int check = 0;
    
    public static ArrayList<MemberTemp> getUserNames() {
    	
    	
    	if(check == 0) {
            try {
                DatabaseBean database = new DatabaseBean();
                members = database.execute_fetch_all("SELECT * FROM Member",-1);
                database.destruct_connection();
            } catch (SQLException e) {
                System.out.println("ERROR OCCURED WHILE PULLING COURSES " + e.getMessage());
            }
            
            for(LinkedHashMap<String, Object> member : members) {
            	//System.out.println("NAME : " + member.get("NAME").toString() + " " + member.get("SURNAME").toString());
            	//System.out.println("MAIL : " + member.get("EMAIL"));
            	userNames.add(new MemberTemp(member.get("NAME").toString() + " " + member.get("SURNAME").toString(), member.get("TC").toString()));
            }
            
            check++;
    	}

    	
        //System.out.println(members);
        

        
       
        
//        userNames.add(new MemberTemp("Ege Uçak", "12345"));
//        userNames.add(new MemberTemp("Bahadýr Adak", "baho"));
//        userNames.add(new MemberTemp("Eyüpcan Bodur", "konyalý"));
//        userNames.add(new MemberTemp("Berk Can Özen", "brkczn"));
//        userNames.add(new MemberTemp("Serhat Saðlýk", "mavi"));
//        userNames.add(new MemberTemp("Sean Green", "seangreen"));
//        userNames.add(new MemberTemp("Keegan Alvarado", "keeganalvarado"));
//        userNames.add(new MemberTemp("Nguyen","oscarnguyen"));
//        userNames.add(new MemberTemp("Carrillo","reginaldcarrillo"));
//        userNames.add(new MemberTemp("Isabelle","Strickland"));
//        userNames.add(new MemberTemp("Alaina","Willis"));
//        userNames.add(new MemberTemp("Tiffani","Phillips"));
//        userNames.add(new MemberTemp("Eugene","Miranda"));
//        userNames.add(new MemberTemp("Brenna","Chandler"));
//        userNames.add(new MemberTemp("Ryan","Wolfe"));
//        userNames.add(new MemberTemp("Caitlyn","Clarke"));
//        userNames.add(new MemberTemp("Caitlin","Lloyd"));
//        userNames.add(new MemberTemp("Graham","Zimmerman"));
//        userNames.add(new MemberTemp("Nathanael","Mclaughlin"));
//        userNames.add(new MemberTemp("Dalton","Sanchez"));
//        userNames.add(new MemberTemp("Esmeralda","Morales"));
//        userNames.add(new MemberTemp("Blake","Lyons"));
//        userNames.add(new MemberTemp("Vincent","Garcia"));

        return userNames;
    }



//    public ArrayList<Trainer> loadTrainers() {
//        ArrayList<Course> courses = new ArrayList<Course>();
//
//        ArrayList<LinkedHashMap<String,Object>> result = null;
//        try {
//            DatabaseBean database = new DatabaseBean();
//            result = database.execute_fetch_all("SELECT * FROM Course",-1);
//            database.destruct_connection();
//        } catch (SQLException e) {
//            System.out.println("ERROR OCCURED WHILE PULLING COURSES " + e.getMessage());
//        }
//
//        if(result == null){
//            System.out.println("LOAD COURSES RESULT LIST IS EMPTY\n");
//        }else{
//            int len = result.size();
//            int x = 0;
//            while(x < len){
//                LinkedHashMap<String,Object> row = result.get(x);
//                Course course = new Course((Integer) row.get("C_ID"),row.get("C_NAME").toString(),row.get("C_DESCRIPTION").toString());
//                courses.add(course);
//                x++;
//            }
//        }
//        this.courses = courses;
//        return courses;
//    }





}



