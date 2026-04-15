package model;

import exception.StorageException;

public interface Storable{
    void storeProduct(Product p)throws StorageException;
}
