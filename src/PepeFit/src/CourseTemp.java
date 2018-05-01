public class CourseTemp {
    private String name;
    private String id;
    private String description;

    CourseTemp(String name, String id, String description) {
        setName(name);
        setId(id);
        setDescription(description);
    }

    public String getCourseName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getCourseId() {
        return id;
    }

    public String getCourseDescription() {
        return description;
    }
}