package vn.edu.iuh.fit.backend.test;

import vn.edu.iuh.fit.backend.repositories.CustomerRepository;
import vn.edu.iuh.fit.backend.repositories.EmployeeRepository;

public class EmployeeTest {
    public static void main(String[] args) {
        EmployeeRepository employeeRespository = new EmployeeRepository();
        System.out.println(employeeRespository.getAll());

        CustomerRepository repository = new CustomerRepository();
        System.out.println(repository.getAll());

    }

}
