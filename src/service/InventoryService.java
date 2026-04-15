package service;

import model.Product;

import javax.xml.crypto.Data;
import java.sql.*;

public class InventoryService {
    //1.SAVE PRODUCT
    public int saveProduct(String name, double price, int stock, boolean needsColdStorage){
        //1. Prepare SQL command with ??? for security
        String sql = "INSERT INTO products(name, price, stock, needsColdStorage) VALUES(?,?,?,?)";
        //2. We open the connection in a try-catch
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

        //3. We replace the question marks with real data
            stmt.setString(1,name);
            stmt.setDouble(2,price);
            stmt.setInt(3,stock);
            stmt.setBoolean(4,needsColdStorage);
            stmt.executeUpdate();

        //4. Extract the generated ID
       try (ResultSet generatedKeys = stmt.getGeneratedKeys()){
           if (generatedKeys.next()){
               System.out.println("Produs salvat cu succes in baza de date!");
               return generatedKeys.getInt(1); //return the id from DB
           }
       }


        }catch (SQLException e){
            System.out.println("Eroare la salvarea produsului: "+e.getMessage());
        }
        return -1;
    }
    //2.DISPLAY INVENTORY
    public void displayInventory(){
        String sql = "SELECT * FROM products WHERE isActive = true";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){
            //execute and receive in rs
            System.out.println("\n--- INVENTAR DIN BAZA DE DATE ---");
            //while there is still a row in table, read it !
            while (rs.next()){
                // Extract the data using the exact column names from MySQL
                int id = rs.getInt("productId");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");
                boolean needsColdStorage = rs.getBoolean("needsColdStorage");
                System.out.println("ID: "+id+" |Nume: "+name+" | Pret: "+price+" | Stoc: "+stock+"| Cold: "+(needsColdStorage ? "DA" : "NU"));
            }
            System.out.println("--------------------------------\n");
        }catch (SQLException e){
            System.out.println("Eroare la citirea din baza de date: "+e.getMessage());
        }
    }
    //2.SHOW DELETE PRODUCT
    public void showDeleteProduct(){
        String sql = "SELECT * FROM products WHERE isActive = false";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){
            //execute and receive in rs
            System.out.println("\n--- INVENTAR DIN BAZA DE DATE ---");
            //while there is still a row in table, read it !
            while (rs.next()){
                // Extract the data using the exact column names from MySQL
                int id = rs.getInt("productId");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");
                System.out.println("ID: "+id+" |Nume: "+name+" | Pret: "+price+" | Stoc: "+stock);
            }
            System.out.println("--------------------------------\n");
        }catch (SQLException e){
            System.out.println("Eroare la citirea din baza de date: "+e.getMessage());
        }
    }
    //3.UPDATE THE PRICE & STOCK
    public void updateProduct(int id, double newPrice, int newStock){
        String sql = "UPDATE products SET price = ?, stock = ? WHERE productId = ? AND isActive = true";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);)
              {
            stmt.setDouble(1,newPrice);
            stmt.setInt(2,newStock);
            stmt.setInt(3,id);

            int rowsAffected=stmt.executeUpdate();

            if (rowsAffected > 0 ){
                System.out.println("Produsul cu ID-ul "+id+" a fost actualizat cu succes!");
            }else {
                System.out.println("Nu am gasit produsul (sau este deja arhivat).");
            }


        }catch (SQLException e){
            System.out.println("Eroare la actualizare: "+e.getMessage());
        }
    }



    //3.DELETE PRODUCT
    public void deleteProduct(int id){
        String sql = "UPDATE products SET isActive = false WHERE productId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            // Replace the question mark with the ID received as a parameter
            stmt.setInt(1,id);
            // executeUpdate() returnează numărul de rânduri afectate/șterse
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0 ){
                System.out.println("Produsul cu ID-UL "+id+" a fost arhivat/dezactivat");
            }else {
                System.out.println("Nu am gasit niciun produs cu ID-UL "+id+" in baza de date");
            }
        }catch (SQLException e){
            System.out.println("Eroare la stergerea produsului: "+e.getMessage());
        }
    }

    //get product by id
    public Product getProductById(int id){
        String sql = "SELECT * FROM  products WHERE productId = ? AND isActive = true";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id);

            try (ResultSet rs = statement.executeQuery()){
                if (rs.next()){
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    int stock = rs.getInt("stock");
                    boolean needsColdStorage = rs.getBoolean("needsColdStorage");

                    // we create the product object and return it
                    return new Product(id,name,price,stock,needsColdStorage);
                }
            }

        }catch (SQLException e){
            System.out.println("Eroare: "+e);

        }
        return null;
    }

    //update product area
    public void updateProductArea(int productId, String areaId){
        String sql = "UPDATE products SET assigned_area = ? WHERE productId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,areaId);
            statement.setInt(2,productId);
            int rowsAffected= statement.executeUpdate();

            if (rowsAffected > 0){
                System.out.println("Repartizarea a fost salvata in baza de date");
            }

        }catch (SQLException e){
            System.out.println("Eroare la salvarea zonei: "+e.getMessage());
        }
    }


}
