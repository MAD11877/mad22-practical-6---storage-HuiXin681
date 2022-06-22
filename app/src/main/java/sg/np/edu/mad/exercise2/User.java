package sg.np.edu.mad.exercise2;

public class User {
    String name;

    String description;

    Integer Id;

    Boolean followed;
    //constructor
    public User(){};
    public User(String n, String desc, int id, boolean f) {
        name = n;
        description = desc;
        Id = id;
        followed = f;
    }

    public String getUsername(){return this.name;}
    public String getDescription(){return this.description;}
    public int getUId(){return this.Id;}
    public boolean getFollowed() {return this.followed;}

    public void setUsername(String name){this.name = name;}
    public void setDescription(String description){this.description = description;}
    public void setUId(int Id){this.Id = Id;}
    public void setFollowed(boolean followed){this.followed = followed;}
}
