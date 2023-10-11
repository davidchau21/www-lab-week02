package vn.edu.iuh.fit.test;

import vn.edu.iuh.fit.repositories.EmployeeRespository;

public class EmployeeTest {
    public static void main(String[] args) {
        EmployeeRespository employeeRespository = new EmployeeRespository();
        System.out.println(employeeRespository.getAll());

    }

}
