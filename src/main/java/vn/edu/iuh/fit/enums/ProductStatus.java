package vn.edu.iuh.fit.enums;

public enum ProductStatus {
    ACTIVE(1),
    IN_ACTIVE(0),
    TEMINATED(-1);
    private int values;
    ProductStatus(int values){
        this.values = values;
    }
    public int getValues(){
        return values;
    }
}
