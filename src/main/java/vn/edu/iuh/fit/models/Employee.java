package vn.edu.iuh.fit.models;

import jakarta.persistence.*;
import vn.edu.iuh.fit.enums.EmpoyeeStatus;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private long id;

    @Column(name = "full_name", length = 150, nullable = false)
    private String fullname;

    @Column(nullable = false)
    private Date dod;

    @Column(unique = true, length = 150)
    private String email;

    @Column(length = 15, nullable = false)
    private String phone;

    @Column(length = 120, nullable = false)
    private String address;

    @Column(nullable = false)
    private EmpoyeeStatus status;

    @OneToMany
    private List<Order> listOrder;

    public Employee() {
    }

    public Employee(long id, String fullname, Date dod, String email, String phone, String address, EmpoyeeStatus status) {
        this.id = id;
        this.fullname = fullname;
        this.dod = dod;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getDod() {
        return dod;
    }

    public void setDod(Date dod) {
        this.dod = dod;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public EmpoyeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmpoyeeStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", dod=" + dod +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}