package vn.edu.iuh.fit.test;

import jakarta.persistence.EntityManager;
import vn.edu.iuh.fit.repositories.EmployeeRespository;

public class EmployeeTest {
    private EntityManager entityManager;

    public EmployeeTest() {
        entityManager = jakarta.persistence.Persistence.createEntityManagerFactory("lab_week_2").createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public static void main(String[] args) {
        // get all employee
        EmployeeRespository employeeRespository = new EmployeeRespository();
        employeeRespository.getAllEmployee().forEach(System.out::println);
    }
}
