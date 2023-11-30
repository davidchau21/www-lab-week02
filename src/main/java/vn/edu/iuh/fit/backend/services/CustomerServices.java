package vn.edu.iuh.fit.backend.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.backend.models.Customer;
import vn.edu.iuh.fit.backend.repositories.CustomerRepository;

import java.util.List;

public class CustomerServices {
    @Inject
    private CustomerRepository dao;

    @Inject
    public CustomerServices() {
        CustomerRepository customerRespository = new CustomerRepository();
        this.dao = customerRespository;
    }

    public List<Customer> getAll() {
        return dao.getAll();
    }

    public Customer searchById(long id) {
        return dao.searchById(id);
    }

    public List<Customer> getFromXToY(int x, int y) {
        return dao.getFromXToY(x, y);
    };

    public boolean add(Customer customer) {
        return dao.add(customer);
    }

    public boolean updateField(long id,Customer customer) {
        return dao.updateField(id, customer);
    }

    public boolean del(long id) {
        return dao.del(id);
    }

}
