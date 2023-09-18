package vn.edu.iuh.fit.enums;

public enum EmpoyeeStatus {

    ACTIVE(1),
    IN_ACTIVE(0),
    TEMINATED(-1);
    private int values;

    EmpoyeeStatus(int values){
        this.values = values;
    }
    private int getValues(){
        return values;
    }
}
