package com.model.order;

import com.persistence.order.OrderDAO;
import com.persistence.order.OrderDAOImpl;

public class OrderServices implements OrderService {

    private OrderDAO orderDAO;

    private OrderDAO getOrderDAO() {
        if (orderDAO != null) {
            return orderDAO;
        }
        orderDAO = new OrderDAOImpl();
        return orderDAO;
    }


    @Override
    public boolean safeOrder(int id) {
        return getOrderDAO().safeOrder(id);
    }
}
