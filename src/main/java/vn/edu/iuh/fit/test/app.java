package vn.edu.iuh.fit.test;

import java.util.List;

import vn.edu.iuh.fit.models.Employee;
import vn.edu.iuh.fit.repositories.EmployeeRespository;

public class app {
    public static void main(String[] args) {
        EmployeeRespository employeeRespository = new EmployeeRespository();
        List<Employee> list = employeeRespository.getAllEmployee();
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }
}
