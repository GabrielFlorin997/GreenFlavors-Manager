package model;

import exception.StorageException;

import java.util.ArrayList;

public class StandardStorage extends WarehouseArea implements Storable{
    private ArrayList<Product> productList;
    public StandardStorage(String areaId, int capacity){
        super(areaId, capacity);
        this.productList = new ArrayList<>();
    }

    @Override
    public void storeProduct(Product p)throws StorageException{
        if (p.isNeedsColdStorage()){
            throw new StorageException("Eroare: Produsul "+p.getName()+" trebuie dus la FRIGIDER, nu aici!");
        }

        //we check the capacity
        if (productList.size() >= getCapacity()){
            throw new StorageException("Eroare: Standard Storage "+getAreaId()+" a atins capacitatea maxima!");
        }

        //we check not to enter the same product twice
           if (productList.contains(p)){
              throw new StorageException("Atentie: "+p.getName()+" se afla deja pe acest raft");
           }


        //add the product
        productList.add(p);
        System.out.println("Produsul "+p.getName()+" a fost adaugat cu succes in Standard Storage!");
    }
}
