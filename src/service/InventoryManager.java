package service;

import exception.StorageException;
import model.ColdStorage;
import model.Product;
import model.Storable;
import model.WarehouseArea;

import java.util.HashMap;

public class InventoryManager {
    private HashMap<Integer, Product> products;
    private HashMap<String, WarehouseArea> warehouseArea;
    private InventoryService inventoryService;

    //-CONSTRUCTOR-
    public InventoryManager() {
        this.products = new HashMap<>();
        this.warehouseArea = new HashMap<>();
        this.inventoryService = new InventoryService();
    }

    //-START HASHMAP PRODUCTS-
    //-ADD PRODUCT-
    public void addProduct(Product p) {
        products.put(p.getId(), p);
        System.out.println("Produsul " + p.getName() + " a fost adaugat in indexul local.");
    }


//-FIND PRODUCT-
public Product findProduct(int id) {
    //search in RAM-SUPER FAST
    if (products.containsKey(id)) {
        return products.get(id);
    }
    //if it's not in RAM i restarted the application we look in DB
    System.out.println("Produsul nu e in memorie. Il caut in baza de date...");
    Product p = inventoryService.getProductById(id);
    if (p != null) {
        //if we found it in the DB, we also put it in the HashMap for next time
        products.put(id, p);
        return p;
    }
    return null;
}

//-DISPLAY INVENTORY-
public void displayInventory() {
    inventoryService.displayInventory();
}

//-END HASHMAP PRODUCTS-

//-START HASHMAP WAREHOUSE-
//-ADD OBJECTS IN WAREHOUSE-
public void addArea(WarehouseArea area) {
    String id = area.getAreaId();
    warehouseArea.put(id, area);

}

//-END HASHMAP PRODUCTS-
public void dispatchProduct(String areaId, Product p) {

    WarehouseArea area = warehouseArea.get(areaId);

    if (area == null){
        System.out.println("Eroare: Zona "+areaId+" nu a fost gasita!");
        return;
    }

    //check if areaId exists in the product or warehousearea hasmap
    if (area instanceof Storable) {
        Storable storableArea = (Storable) area;
        try {
            storableArea.storeProduct(p);
            //update DB
            inventoryService.updateProductArea(p.getId(),areaId);
        }catch (StorageException e){
            System.out.println("Eroare stocare: "+e.getMessage());
        }

    }else {
        System.out.println("Zona "+areaId+" nu este destinata depozitarii");
    }
}

//-METHODS-
public void checkAllTemperatures() {
    for (WarehouseArea w : warehouseArea.values()) {
        if (w instanceof ColdStorage) {
            ColdStorage coldStorage = (ColdStorage) w;
            System.out.println("Zona " + coldStorage.getAreaId() + " are temperatura: " + coldStorage.getTemperature() + " grade.");
        } else {
            System.out.println("Nu exista");
        }


    }
}

public void printLowStockProducts(){
    System.out.println("\n --- ALERTA STOC CRITIC (sub 10 unitati) ---");
    boolean found = false;
    for (Product p : products.values()){
        if (p.getStock() < 10){
            System.out.println("Nume: "+p.getName()+" | Stoc actual: "+p.getStock());
            found=true;
        }
    }
    if (!found){
        System.out.println("Toate produsele au stoc suficient.");
    }
}

//process sale
    public void sellProduct(int productId, int quantity){
        inventoryService.processSale(productId,quantity);
    }

}
