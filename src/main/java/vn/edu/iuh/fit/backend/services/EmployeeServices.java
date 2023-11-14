package vn.edu.iuh.fit.backend.services;

import java.util.List;
import java.util.Optional;

import vn.edu.iuh.fit.backend.enums.EmployeeStatus;
import vn.edu.iuh.fit.backend.models.Employee;
import vn.edu.iuh.fit.backend.repositories.EmployeeRespository;

public class EmployeeServices {
    private EmployeeRespository employeeRespository;

    public EmployeeServices() {
        employeeRespository = new EmployeeRespository();
    }

    public void insertEmployee(Employee employee) {
        employeeRespository.insertEmployee(employee);
    }

    public boolean delete(long id) {
        Optional<Employee> op = employeeRespository.findById(id);
        if (op.isPresent()) {
            Employee employee = op.get();
            employee.setStatus(EmployeeStatus.IN_ACTIVE);
            return true;
        }
        return false;
    }

    public boolean activeEmp(long id) {
        Optional<Employee> op = employeeRespository.findById(id);
        if (op.isPresent()) {
            Employee employee = op.get();
            employee.setStatus(EmployeeStatus.ACTIVE);
            return true;
        }
        return false;
    }

    public List<Employee> getAll() {
        return employeeRespository.getAll();
    }

    public Optional<Employee> findById(long id) {
        return employeeRespository.findById(id);
    }

}
