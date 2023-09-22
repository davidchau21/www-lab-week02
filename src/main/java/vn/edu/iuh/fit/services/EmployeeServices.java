package vn.edu.iuh.fit.services;

import java.util.List;
import java.util.Optional;

import vn.edu.iuh.fit.enums.EmpoyeeStatus;
import vn.edu.iuh.fit.models.Employee;
import vn.edu.iuh.fit.repositories.EmployeeRespository;

public class EmployeeServices {
    private EmployeeRespository employeeRespository;

    public EmployeeServices() {
        employeeRespository = new EmployeeRespository();
    }

    public void insertEmployee(Employee employee) {
        employeeRespository.insertEmployee(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeRespository.updateEmployee(employee);
    }

    public boolean delete(long id) {
        Optional<Employee> op = employeeRespository.findById(id);
        if (op.isPresent()) {
            Employee employee = op.get();
            employee.setStatus(EmpoyeeStatus.IN_ACTIVE);
            return true;
        }
        return false;
    }

    public boolean activeEmp(long id) {
        Optional<Employee> op = employeeRespository.findById(id);
        if (op.isPresent()) {
            Employee employee = op.get();
            employee.setStatus(EmpoyeeStatus.ACTIVE);
            return true;
        }
        return false;
    }

    public List<Employee> getAll() {
        return employeeRespository.getAllEmployee();
    }

    public Optional<Employee> findById(long id) {
        return employeeRespository.findById(id);
    }

}