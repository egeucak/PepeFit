public class CourseTemp {
    private String name;
    private String id;
    private String description;

    CourseTemp(String name, String id, String description) {
        setName(name);
        setId(id);
        setDescription(description);
    }

    public String getName() {
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

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}