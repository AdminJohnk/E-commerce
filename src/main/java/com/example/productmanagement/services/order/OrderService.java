package com.example.productmanagement.services.order;

import com.example.productmanagement.dtos.order.OrderDTO;
import com.example.productmanagement.entities.Order;

import java.security.Principal;
public interface OrderService {
    Order getOrder(String id);
    Order create(OrderDTO dto, Principal principal);
    Order update(String id, OrderDTO dto, Principal principal);
    Order delete(String id);
}