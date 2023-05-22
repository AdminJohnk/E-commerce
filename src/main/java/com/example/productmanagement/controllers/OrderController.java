package com.example.productmanagement.controllers;

import com.example.productmanagement.dtos.order.OrderDTO;
import com.example.productmanagement.entities.Order;
import com.example.productmanagement.services.order.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable String id) {
        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO dto, Principal principal) {
        return new ResponseEntity<>(orderService.create(dto, principal), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable String id, @RequestBody OrderDTO dto, Principal principal) {
        return new ResponseEntity<>(orderService.update(id, dto, principal), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable String id) {
        return new ResponseEntity<>(orderService.delete(id), HttpStatus.OK);
    }
}
