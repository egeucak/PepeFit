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

public class ListCourseBean {
    private ArrayList<CourseTemp> courseNames = new ArrayList<CourseTemp>();

    // Creating and Object for Courses Class. Because loadCourses() function is in there.
    private Courses courses = new Courses();

    public ArrayList<CourseTemp> getCourseNames() {
        courseNames.add(new CourseTemp("Sokuş", "sokus", "Sikiş sokuş mala vuruş"));
        courseNames.add(new CourseTemp("Boko", "Moko", "Totonoto moto"));

        return courseNames;
    }
//
//    public void setCourseNames(ArrayList<Course> courseNames) {
//        this.courseNames = courseNames;
//    }

//    private ArrayList<Course> courseNames = courses.loadCourses();

//
//    public ArrayList<CourseTemp> getCourseNames() {
//        courseNames.add(new CourseTemp("Body Fit", "bodyfit", "Bodymizi fit yapıyoruz"));
//        courseNames.add(new CourseTemp("Mac Stronger", "macstronger", "Macte strong oluyoruz"));
//        courseNames.add(new CourseTemp("GFX", "gfx", "gfx ???"));
//        courseNames.add(new CourseTemp("Fit Step", "fitstep", "fit stepler atıyoruz"));
//        courseNames.add(new CourseTemp("Fit Attack", "fitattack", "fit attack dan dan dan öldün çık"));
//        courseNames.add(new CourseTemp("Fit Dance", "fitdance", "fit halay çekiyoruz"));
//        courseNames.add(new CourseTemp("Exp. Cycling", "expcycling" ,"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
//        courseNames.add(new CourseTemp("Cycle","cycle", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
//        courseNames.add(new CourseTemp("Yoga","yoga", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
//        courseNames.add(new CourseTemp("Pilates Studio", "pilatesstud","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
//        courseNames.add(new CourseTemp("Energy Step", "energystep","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
//        courseNames.add(new CourseTemp("Zumba","zumba", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
//        courseNames.add(new CourseTemp("Abs Crunch","abscrunch", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
//        courseNames.add(new CourseTemp("Crunch and Burn","crunchnburn", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
//        courseNames.add(new CourseTemp("Lifting Heavy Feelings","feelspepeman", "Eski günleri hatırlıyoruz. Kaçırdığımız fırsatlar, bitirdiğimiz arkadaşlıklar, kırdığımız kalpler, boşa giden emeklerimiz ve daha fazlası bizi kovalıyor. Biz de kaçmaya çalışıyoruz."));
//
//        return courseNames;
//    }



}