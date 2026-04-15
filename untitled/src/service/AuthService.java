package service;

import service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService {



    public boolean login(String userName, String password){
    String sql = "SELECT * FROM users WHERE userName = ? AND password = ? AND isActive =true";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setString(1,userName);
        statement.setString(2,password);

        try (ResultSet rs = statement.executeQuery()){
            if (rs.next()){

                System.out.println("Bun venit: "+userName);
            return true;
            }else {
                System.out.println("Eroare: username gresit sau parola gresita!");
                return false;
            }
        }
    }catch (SQLException e){
        System.out.println("Eroare: "+e.getMessage());
        return false;
    }
    }
}
