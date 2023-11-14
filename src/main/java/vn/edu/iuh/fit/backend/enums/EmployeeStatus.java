package vn.edu.iuh.fit.backend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EmployeeStatus {

    ACTIVE(1),
    IN_ACTIVE(0),
    TEMINATED(-1);

    private int value;

    EmployeeStatus(int values) {
        this.value = values;
    }

    @JsonValue
    public int getValues() {
        return value;
    }

    @JsonCreator
    public static EmployeeStatus fromValue(int value) {
        for (EmployeeStatus status : EmployeeStatus.values()) {
            if (status.getValues() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid EmployeeStatus value: " + value);
    }
}
