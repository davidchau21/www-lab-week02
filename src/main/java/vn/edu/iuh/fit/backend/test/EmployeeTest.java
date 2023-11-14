package vn.edu.iuh.fit.backend.test;

import vn.edu.iuh.fit.backend.repositories.EmployeeRepository;

public class EmployeeTest {
    public static void main(String[] args) {
        EmployeeRepository employeeRespository = new EmployeeRepository();
        System.out.println(employeeRespository.getAll());

    }

}
