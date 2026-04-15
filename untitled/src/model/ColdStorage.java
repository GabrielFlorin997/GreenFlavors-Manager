package model;

import exception.StorageException;
import java.util.ArrayList;
public class ColdStorage extends WarehouseArea implements Storable{
    //private instance field
    private double temperature;
    private ArrayList<Product> productList;
    public ColdStorage(String areaId, int capacity, double temperature){
        super(areaId, capacity);
        this.temperature=temperature;
        this.productList=new ArrayList<>();
    }
    //-INTERFACE STORABLE-
    @Override
    public void storeProduct(Product p) throws StorageException{
        //we check if the product is compatible
        if (!p.isNeedsColdStorage()){
            throw new StorageException("Eroare: Produsul "+p.getName()+" NU este compatibil cu zona rece!");
        }
        //we check the capacity
     if (productList.size() >= getCapacity()){
         throw new StorageException("Eroare: Frigiderul "+getAreaId()+" a atins capacitatea maxima!");
     }
        //we check not to enter the same product twice
        if (productList.contains(p)){
            throw new StorageException("Atentie: "+p.getName()+" se afla deja pe acest raft");
        }



        //we add the product and display the details
     productList.add(p);
        System.out.println("Produsul "+p.getName()+" a fost adaugat cu succes in frigider!");

    }

    //-GETTER-
    public double getTemperature(){
        return this.temperature;
    }
    //-SETTER-
    public void setTemperature(double temperature){
        if (temperature < -30 || temperature > 20 ){
            throw new IllegalArgumentException("Eroare, temperatura neconforma!");
        }else {
            this.temperature=temperature;
        }
    }
}
