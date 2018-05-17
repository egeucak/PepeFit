public class MemberTemp {
    private String name;
    public static String id;

    MemberTemp(String name, String id) {
        setName(name);
        setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public static String getId() {
		return id;
	}

	public static void setId(String id) {
		MemberTemp.id = id;
	}

    
}
