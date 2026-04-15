package model;

public abstract class WarehouseArea {
    //private instance fields
    private String areaId;
    private int capacity;

    //-CONSTRUCTOR-
    public WarehouseArea(String areaId, int capacity){
        this.areaId=areaId;
        this.capacity=capacity;
    }

    //-GETTERS-
    public String getAreaId(){
        return this.areaId;
    }
    public int getCapacity(){
        return this.capacity;
    }

    //-SETTER-
    public void setCapacity(int capacity){
        if (capacity < 0 ){
            throw new IllegalArgumentException(capacity+" nu poate fi mai mica decat 0");
        }else {
            this.capacity=capacity;
        }
    }

     
}
