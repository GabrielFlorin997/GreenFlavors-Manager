package model;

public class Product {
    //private instance fields
    private int id;
    private String name;
    private double price;
    private int stock;
    private boolean needsColdStorage;

    //-CONSTRUCTOR-
    public Product(int id, String name, double price, int stock, boolean needsColdStorage) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.needsColdStorage=needsColdStorage;
    }


    //CONSTRUCTOR FOR NEW GOODS
    public Product(String name, double price, int stock, boolean needsColdStorage){
        this.name=name;
        this.price=price;
        this.stock=stock;
        this.needsColdStorage=needsColdStorage;
    }
    //-GETTERS-
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getStock() {
        return this.stock;
    }

    public boolean isNeedsColdStorage(){
        return this.needsColdStorage;
    }

    //-SETTERS-
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException(price + " nu poate fi mai mic decat 0");
        } else {
            this.price = price;
        }
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException(id + " nu poate fi mai mic decat 0");
        } else {
            this.id = id;
        }
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException(stock + " nu poate fi mai mic decat 0");
        } else {
            this.stock = stock;
        }
    }
    //-INTERFACE STORABLE-


}
