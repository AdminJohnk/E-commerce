package com.example.productmanagement.services.order;

import com.example.productmanagement.dtos.order.OrderDTO;
import com.example.productmanagement.entities.Order;
import com.example.productmanagement.exceptions.InvalidException;
import com.example.productmanagement.exceptions.NotFoundException;
import com.example.productmanagement.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.security.Principal;
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getOrder(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Đơn hàng có id %s không tồn tại", id)));
    }

    @Override
    public Order create(OrderDTO dto, Principal principal) {
        if (ObjectUtils.isEmpty(dto.getCustomerName())) {
            throw new InvalidException("Tên khách hàng không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getItems())) {
            throw new InvalidException("Danh sách sản phẩm không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getTotalAmount())) {
            throw new InvalidException("Tổng tiền không được bỏ trống");
        }
        Order order = new Order();
        order.setCustomerName(dto.getCustomerName().trim());
        order.setItems(dto.getItems());
        order.setTotalAmount(dto.getTotalAmount());
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order update(String id, OrderDTO dto, Principal principal) {
        Order order = getOrder(id);
        if (ObjectUtils.isEmpty(dto.getCustomerName())) {
            throw new InvalidException("Tên khách hàng không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getItems())) {
            throw new InvalidException("Danh sách sản phẩm không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getTotalAmount())) {
            throw new InvalidException("Tổng tiền không được bỏ trống");
        }
        order.setCustomerName(dto.getCustomerName().trim());
        order.setItems(dto.getItems());
        order.setTotalAmount(dto.getTotalAmount());
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order delete(String id) {
        Order order = getOrder(id);
        orderRepository.delete(order);
        return order;
    }
}
