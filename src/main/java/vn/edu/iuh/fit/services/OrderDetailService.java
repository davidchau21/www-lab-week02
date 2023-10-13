package vn.edu.iuh.fit.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.models.OrderDetail;
import vn.edu.iuh.fit.repositories.OrderDetailResponsitory;

import java.util.List;

public class OrderDetailService {
    @Inject
    private OrderDetailResponsitory daoResponsitory;

    @Inject
    public OrderDetailService(OrderDetailResponsitory orderDetailResponsitory) {
        this.daoResponsitory = orderDetailResponsitory;
    }

    public OrderDetail searchById(OrderDetail orderDetail) {
        return daoResponsitory.searchById(orderDetail);
    }

    public List<OrderDetail> getAll(long orders_id) {
        return daoResponsitory.getAll(orders_id);
    }

    public boolean add(OrderDetail obj) {
        return daoResponsitory.add(obj);
    }

    public boolean del(OrderDetail obj) {
        return daoResponsitory.del(obj);
    }

}
