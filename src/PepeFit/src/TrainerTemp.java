public class TrainerTemp {
    private String name;
    private String id;
    private String bio;
    private String mail;

    TrainerTemp(String name, String id, String bio) {
        setName(name);
        setId(id);
        setBio(bio);
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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

    public String getId() {
        return id;
    }
}