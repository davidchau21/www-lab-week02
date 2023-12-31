package vn.edu.iuh.fit.backend.repositories;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBConnect {
    private static DBConnect instance;
    private EntityManagerFactory emf;

    private DBConnect() {
        emf = Persistence.createEntityManagerFactory("lab_week_2");
    }

    public static DBConnect getInstance() {
        if (instance == null)
            instance = new DBConnect();
        return instance;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }
}
