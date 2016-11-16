package com.example.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.order.OrderStateMachineConfiguration.OrderState;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findByCurrentState(OrderState currentState, Pageable pageable);

}