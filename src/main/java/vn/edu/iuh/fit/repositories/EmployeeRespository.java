package vn.edu.iuh.fit.repositories;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import vn.edu.iuh.fit.enums.EmpoyeeStatus;
import vn.edu.iuh.fit.models.Employee;

public class EmployeeRespository {
    private EntityManager entityManager;

    public EmployeeRespository() {
        entityManager = Persistence.createEntityManagerFactory("lab_week_2").createEntityManager();
    }

    // insert employee
    public void insertEmployee(Employee employee) {
        entityManager.persist(employee);
    }

    // update employee
    public void updateEmployee(Employee employee) {
        entityManager.merge(employee);
    }

    // delete employee
    public void deleteEmployee(Employee employee) {
        entityManager.remove(employee);
    }

    // find employee by id
    public Employee findEmployeeById(int id) {
        return entityManager.find(Employee.class, id);
    }

    // set status employee
    public void setStatusEmployee(Employee employee, EmpoyeeStatus status) {
        employee.setStatus(status);
    }

    // get all employee
    public List<Employee> getAllEmployee() {
        return entityManager.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }

    public Optional<Employee> findById(long id) {
        TypedQuery<Employee> query = entityManager.createQuery("select e from Employee e where e.id=:id",
                Employee.class);
        query.setParameter("id", id);
        Employee emp = query.getSingleResult();
        return emp == null ? Optional.empty() : Optional.of(emp);
    }

}
