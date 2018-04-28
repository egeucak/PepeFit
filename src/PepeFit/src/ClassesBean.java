import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class ClassesBean{

    private String className;

    private static Map<String,Object> classNames = new LinkedHashMap<String, Object>();

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void editActionClassName(String className) {
        if(className.equals("Class1")) {
            setClassName("Class1");
        }
        else if(className.equals("Class2")) {
            setClassName("Class2");
        }
        else if(className.endsWith("Class3")) {
            setClassName("Class3");
        }
    }

    static {
        classNames.put("Class1", "Class1");
        classNames.put("Class2", "Class2");
        classNames.put("Class3", "Class3");
    }

    public Map<String,Object> fillClasses() {
        return classNames;
    }


}


