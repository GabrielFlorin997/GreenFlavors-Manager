import service.DatabaseConnection;
import service.InventoryManager;
import ui.ConsoleMenu;

import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn != null){
            System.out.println("Conectare reusita la baza de date!");
        }else {
            System.out.println("Ceva nu a mers bine...");
        }

        //create the manager
        InventoryManager manager = new InventoryManager();

        //-CREATE THE INTERFACE-
        ConsoleMenu menu = new ConsoleMenu(manager);

        //PLAY THE BUTTON START
        menu.start();

    }
}