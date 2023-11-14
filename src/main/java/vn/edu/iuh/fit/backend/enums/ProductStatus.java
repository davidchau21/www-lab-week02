package vn.edu.iuh.fit.backend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ProductStatus {
    ACTIVE(1), // dang kinh doanh
    IN_ACTIVE(0), // ngung kinh doanh
    TEMINATED(-1); // khong ban nua

    private int values;

    ProductStatus(int values) {
        this.values = values;
    }

    public int getValues() {
        return values;
    }

    @JsonCreator
    public static ProductStatus fromValue(int value) {
        for (ProductStatus status : ProductStatus.values()) {
            if (status.getValues() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid ProductStatus value: " + value);
    }
}
