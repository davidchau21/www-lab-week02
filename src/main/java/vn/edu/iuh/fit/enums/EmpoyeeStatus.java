package vn.edu.iuh.fit.enums;

public enum EmpoyeeStatus {

    ACTIVE(1),
    IN_ACTIVE(0),
    TEMINATED(-1);

    private int value;

    EmpoyeeStatus(int values) {
        this.value = values;
    }

    public int getValues() {
        return value;
    }
}
