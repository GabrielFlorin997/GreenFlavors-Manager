package ui;


import model.ColdStorage;
import model.Product;
import model.StandardStorage;
import service.InventoryManager;
import java.sql.Connection;
import java.util.Scanner;
import service.InventoryService;
import service.AuthService;
import service.DatabaseConnection; // În caz că ai nevoie de ea aici
public class ConsoleMenu {
    //-CREATE THE MANAGER-
    private InventoryManager manager;
    //-CREATE SCANNER-
    private Scanner scanner = new Scanner(System.in);
    private AuthService authService = new AuthService();


    //-CONSTRUCTOR-
    public ConsoleMenu(InventoryManager manager) {
        this.manager = manager;
        this.scanner = new Scanner(System.in);
    }


    public void start() {
        int attempts = 3;
        boolean isAuthenticated = false;
        System.out.println("--- SECURE LOGIN ---");
        while (attempts > 0 ){
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
          boolean isValid =authService.login(username,password);
            if (isValid){
                isAuthenticated = true;
                System.out.println("Acces permis!");
                break;
            }else {
                attempts--;
                System.out.println("Date gresite! Mai ai "+attempts+" ramase.");
            }
        }

        if (!isAuthenticated){
            System.out.println("Acces blocat. Programul se va inchide!");
            return;
        }




        System.out.println("Meniul a pornit!");
        while (true) {
            System.out.println("1.Adauga produs");
            System.out.println("2.Stergere produs");
            System.out.println("3.Afiseaza inventor");
            System.out.println("4.Adauga Zona Depozitare");
            System.out.println("5.Repartizeaza Produs in Zona");
            System.out.println("6.Afiseaza produse scoase de la vanzare");
            System.out.println("7.Actualizare Pret & Stoc");
            System.out.println("8.Verifica Stoc Critic");
            System.out.println("9.Iesire");

            int choiceOption = -1;
            //-CHOICE OPTION-
            try {
                choiceOption = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Eroare: Te rog sa introduci un numar valid");
                continue;
            }

            switch (choiceOption) {
                case 1:
                    handleAddProduct();
                    break;

                case 2:
                    handleDeleteProduct();
                    break;

                case 3:
                    InventoryService dbServiceForDisplay = new InventoryService();
                    dbServiceForDisplay.displayInventory();
                    //manager.displayInventory();
                    break;

                case 4:
                    handleAddArea();
                    break;

                case 5:
                    handleDispatchProduct();
                    break;

                case 6:
                    InventoryService dvServiceForDeleteProduct = new InventoryService();
                    dvServiceForDeleteProduct.showDeleteProduct();
                    break;
                case 7:
                    updateProduct();
                    break;
                case 8:
                    manager.printLowStockProducts();
                    break;
                case 9:
                    System.out.println("La revedere");
                    return;
                default:
                    System.out.println("Optiune invalida!");
            }

        }

    }

    private void handleAddProduct() {
        //ADD PRODUCT
        System.out.println("-Sectiunea Adauga produs in Baza de Date-");
        System.out.println("Adauga nume: ");
        String nameProduct = scanner.nextLine();

        System.out.println("Adauga pret: ");
        double priceOfProduct = Double.parseDouble(scanner.nextLine());

        System.out.println("Adauga Stoc: ");
        int stockOfProduct = Integer.parseInt(scanner.nextLine());
        System.out.println("Produsul are nevoie de depozitare la rece? 1 - DA /  2 - NU");
        int coldChoice = Integer.parseInt(scanner.nextLine());
         boolean needsCold;

         if (coldChoice == 1){
             needsCold = true; //if user chose 1
         }else {
             needsCold=false;  //if user chose 2
         }
            //we create the local object which still has id = 0;
         Product newProduct = new Product(nameProduct,priceOfProduct,stockOfProduct,needsCold);

            //we save it in the DB and get the real Id
            InventoryService dvService = new InventoryService();
            int generatedId = dvService.saveProduct(newProduct.getName(), newProduct.getPrice(), newProduct.getStock(), newProduct.isNeedsColdStorage());
            //update the ID in the Java object
            if (generatedId != -1){
                newProduct.setId(generatedId);
                //save local+db
                manager.addProduct(newProduct);
                System.out.println("Adaugat cu succes! ID-ul alocat este: "+generatedId);
            }else {
            System.out.println("Eroare: Produsul nu a putut fi salvat in baza de date.");
        }
    }

    private void handleDeleteProduct(){
        System.out.println("Introdu ID-ul produsului pe care il doresti sa il stergi: ");
        int deleteProductId = Integer.parseInt(scanner.nextLine());
        InventoryService dvService = new InventoryService();
        dvService.deleteProduct(deleteProductId);
    }

    private void updateProduct(){
        System.out.println("Introdu ID-ul produsului pe care il doresti sa il modifici: ");
        int updateProductId = Integer.parseInt(scanner.nextLine());
        System.out.println("Introdu noul pret: ");
       double newPrice =  Double.parseDouble(scanner.nextLine());
        System.out.println("Introdu noul stoc: ");
        int updateStock = Integer.parseInt(scanner.nextLine());
        InventoryService dv = new InventoryService();
        dv.updateProduct(updateProductId,newPrice,updateStock);
    }

    //ADD STORAGE AREA
    private void handleAddArea() {
        System.out.println("Alege ce tip de zona doresti sa creezi:");
        while (true) {
            System.out.println("1.Cold Storage (Frigider)");
            System.out.println("2.Standard Shelf (Raft)");
            System.out.println("3.Meniu Principal");
            int choiceOption = -1;
            //-CHOICE OPTION-
            try {
                choiceOption = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Eroare: Te rog sa introduci un numar valid");
                continue;
            }
          switch (choiceOption){
              case 1:
                  coldStorage();
              break;
              case 2:
                  standardShelf();
                  break;
              case 3:
              return;
              default:
                  System.out.println("Optiune invalida!");
          }

            }

        }
        //handleAddArea-COLD STORAGE
        private void coldStorage(){
        //String areaId, int capacity, double temperature
            System.out.println("Introdu Area id:");
            String areaiD = scanner.nextLine();
            System.out.println("Introdu Capacitatea:");
            int capacity = Integer.parseInt(scanner.nextLine());
            System.out.println("Introdu Temperatura:");
            double temperature = Double.parseDouble(scanner.nextLine());
            manager.addArea(new ColdStorage(areaiD,capacity,temperature));
            System.out.println("Adaugare cu succes!");
            return;
        }
    //handleAddArea-STANDARD SHELF
        private void standardShelf(){
            System.out.println("Introdu Area ID penmtru Raft: ");
            String areaId = scanner.nextLine();
            System.out.println("Introdu Capacitatea: ");
            int capacity = Integer.parseInt(scanner.nextLine());

            //we create the zone and add it to the manager
            manager.addArea(new StandardStorage(areaId,capacity));
            System.out.println("Raftul standard a fost creat cu succes!");


        }

        private void handleDispatchProduct(){
            System.out.println("Id-ul Zonei:");
            String areaiD = scanner.nextLine();
            System.out.println("Id-ul produsului:");
            int productId = Integer.parseInt(scanner.nextLine());
            Product foundProduct = manager.findProduct(productId);

            if (foundProduct == null){
                System.out.println("Eroare: Produsul nu exista");
                return;
            }
            manager.dispatchProduct(areaiD, foundProduct);

        }


    }

