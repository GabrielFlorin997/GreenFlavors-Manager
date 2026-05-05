package model;

public class User {
    //private instance fields
    private int userId;
    private String userName;
    private String password;
    private boolean isActive;

    //constructor for class
    public User(int userId, String userName, String password, boolean isActive){
        this.userId = userId;
        this.userName=userName;
        this.password=password;
        this.isActive=isActive;
    }

    //constructor for databse
    //constructor for class
    public User(String userName, String password){
        this.userName=userName;
        this.password=password;
    }

    //getters
    public int getUserId() {
        return userId;
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
