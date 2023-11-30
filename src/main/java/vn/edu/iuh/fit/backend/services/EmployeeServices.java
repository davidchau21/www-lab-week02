package vn.edu.iuh.fit.backend.services;

import java.util.List;
import java.util.Optional;

import vn.edu.iuh.fit.backend.enums.EmployeeStatus;
import vn.edu.iuh.fit.backend.models.Employee;
import vn.edu.iuh.fit.backend.repositories.EmployeeRepository;

public class EmployeeServices {
    private EmployeeRepository employeeRespository;

    public EmployeeServices() {
        employeeRespository = new EmployeeRepository();
    }

    public boolean insertEmployee(Employee employee) {
        return employeeRespository.add(employee);
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

    public List<Employee> getFromXToY(int x, int y){
        return employeeRespository.getFromXToY(x,y);
    };

    public boolean updateField(long id, String nameField, String newValue){
        return employeeRespository.updateField(id,nameField,newValue);
    }
    public boolean del(long id){
        return employeeRespository.del(id);
    }


}
