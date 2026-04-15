package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    //adresa depozitului (localhost:3306) si numele cladirii (greenflavors_db)
    private static final String URL = "jdbc:mysql://localhost:3306/greenflavors_db";
    private static final String USER = "root";
    private static final String PASSWORD = System.getenv("DB_PASSWORD");


    public static Connection getConnection(){
        if(PASSWORD == null){
            throw new RuntimeException("DB_PASSWORD nu este setat");
        }
        try {
            return DriverManager.getConnection(URL,USER,PASSWORD);
        }catch (SQLException e){
            System.out.println("Eroare: Nu m-am putut conecta la baza de date!");
            e.printStackTrace();
            return null;
        }
    }
}