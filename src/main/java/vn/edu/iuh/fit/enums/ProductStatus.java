package vn.edu.iuh.fit.enums;

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
}
