public class Users {
    //private instance fields
    private int userdId;
    private String userName;
    private String password;
    private boolean isActive;

    //constructor for class
    public Users(int userdId, String userName, String password, boolean isActive){
        this.userdId=userdId;
        this.userName=userName;
        this.password=password;
        this.isActive=isActive;
    }

    //constructor for databse
    //constructor for class
    public Users(String userName, String password){
        this.userName=userName;
        this.password=password;
    }

    //getters
    public int getUserdId() {
        return userdId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return isActive;
    }
}
