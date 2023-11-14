package vn.edu.iuh.fit.backend.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.edu.iuh.fit.backend.repositories.DBConnect;

public class Mapping {
    public static void main(String[] args) {
        EntityManager em = DBConnect.getInstance().getEmf().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        try {
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
        }

    }
}
