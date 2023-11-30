package vn.edu.iuh.fit.backend.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.backend.enums.EmployeeStatus;
import vn.edu.iuh.fit.backend.models.Employee;

public class EmployeeRepository {
    private EntityManager em = null;
    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public EmployeeRepository() {
        this.em = DBConnect.getInstance().getEmf().createEntityManager();

    }

    // get all employee
    public List<Employee> getAll() {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            List<Employee> employees = em.createNativeQuery("select * from employees ",Employee.class).getResultList();
            tr.commit();
            return employees;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    public List<Employee> getFromXToY(int x, int y){
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();

            int from = y-x+1;
            int to = x-1;

            String sql = "SELECT * FROM employee ORDER BY full_name where status = 2 OR status = 1 LIMIT "+from +" OFFSET "+to;

            List<Employee> list = em.createNativeQuery(sql, Employee.class).getResultList();

            tr.commit();
            return list;
        } catch (Exception e){
            logger.info(e.getMessage());
            tr.rollback();
        }
        return null;
    }

    // insert employee
    public boolean add(Employee employee) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(employee);
            tr.commit();
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            tr.rollback();
        }
        return false;
    }

    // set status employee
    public void setStatusEmployee(Employee employee, EmployeeStatus status) {
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

    public Employee searchById(long id) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();

            Employee employee = em.find(Employee.class, id);

            tr.commit();
            return employee;
        } catch (Exception e) {
            logger.info(e.getMessage());
            tr.rollback();
        }
        return null;
    }



    public boolean updateField(long id, String nameField, String newValue) {
        EntityTransaction tr = em.getTransaction();
        Employee employee = searchById(id);
        if (employee == null)
            return false;
        try {
            tr.begin();
            switch (nameField) {
                case "address":
                    employee.setAddress(newValue);
                    break;
                case "dob":
                    LocalDateTime dateTime = LocalDateTime.parse(newValue);
                    employee.setDob(dateTime);
                    break;
                case "email":
                    employee.setEmail(newValue);
                    break;
                case "full_name":
                    employee.setFullName(newValue);
                    break;
                case "phone":
                    employee.setPhone(newValue);
                    break;
                case "status":
                    if (newValue.equals("-1")) {
                        employee.setStatus(EmployeeStatus.TEMINATED);
                    } else if (newValue.equals("0")) {
                        employee.setStatus(EmployeeStatus.IN_ACTIVE);
                    } else
                        employee.setStatus(EmployeeStatus.ACTIVE);
                    break;
            }
            tr.commit();
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            tr.rollback();
        }
        return false;
    }

    public boolean del(long id) {
        EntityTransaction tr = em.getTransaction();
        Employee employee = searchById(id);
        if (employee == null)
            return false;
        try {
            tr.begin();

            employee.setStatus(EmployeeStatus.TEMINATED);

            tr.commit();
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            tr.rollback();
        }
        return false;
    }

}
