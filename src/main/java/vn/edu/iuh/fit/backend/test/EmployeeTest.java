package vn.edu.iuh.fit.backend.test;

import vn.edu.iuh.fit.backend.repositories.EmployeeRespository;

public class EmployeeTest {
    public static void main(String[] args) {
        EmployeeRespository employeeRespository = new EmployeeRespository();
        System.out.println(employeeRespository.getAll());

    }

}
