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

public class ListCourseTimesBean{
	
	public ArrayList<String> loadCourseTimes(ArrayList<Object> trainer_times){
        ArrayList<String> courseTimes = new ArrayList<String>();
        courseTimes = (ArrayList<String>)trainer_times.get(2);
        return courseTimes;
    }
}