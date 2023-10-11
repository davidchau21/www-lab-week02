package vn.edu.iuh.fit.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.models.Customer;
import vn.edu.iuh.fit.repositories.CustomerRespository;

import java.util.List;

public class CustomerServices {
    @Inject
    private CustomerRespository dao;

    @Inject
    public CustomerServices(CustomerRespository customerRespository) {
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

    public boolean updateField(long id, String nameField, String newValue) {
        return dao.updateField(id, nameField, newValue);
    }

    public boolean del(long id) {
        return dao.del(id);
    }

}
