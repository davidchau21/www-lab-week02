package vn.edu.iuh.fit.repositories;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import vn.edu.iuh.fit.enums.EmpoyeeStatus;
import vn.edu.iuh.fit.models.Employee;

public class EmployeeRespository {
    private EntityManager em = null;

    public EmployeeRespository() {
        this.em = DBConnect.getInstance().getEmf().createEntityManager();

    }

    // get all employee
    public List<Employee> getAll() {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            List<Employee> employees = em
                    .createNativeQuery("select * from employee order by full_name where status = 2 or status = 1",
                            Employee.class)
                    .getResultList();
            tr.commit();
            return employees;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    // insert employee
    public boolean insertEmployee(Employee employee) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(employee);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }

    // set status employee
    public void setStatusEmployee(Employee employee, EmpoyeeStatus status) {
        employee.setStatus(status);
    }

    // get all employee
    public List<Employee> getAllEmployee() {
        return em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }

    public Optional<Employee> findById(long id) {
        TypedQuery<Employee> query = em.createQuery("select e from Employee e where e.id=:id",
                Employee.class);
        query.setParameter("id", id);
        Employee emp = query.getSingleResult();
        return emp == null ? Optional.empty() : Optional.of(emp);
    }

}
