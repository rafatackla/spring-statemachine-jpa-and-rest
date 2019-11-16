package com.example.order;

import java.io.Serializable;

import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Component;

import com.example.ContextEntity;
import com.example.DefaultStateMachineAdapter;
import com.example.order.domain.Order;
import com.example.order.enums.OrderEvent;
import com.example.order.enums.OrderState;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Component
@RequiredArgsConstructor
public class OrderCreatedEventListener extends AbstractRepositoryEventListener<Order> {

    final DefaultStateMachineAdapter<OrderState, OrderEvent, ContextEntity<OrderState, OrderEvent, ? extends Serializable>> orderStateMachineAdapter;

    @Override
    @SneakyThrows
    protected void onBeforeCreate(Order order) {
        orderStateMachineAdapter.persist(orderStateMachineAdapter.create(), order);
    }

}
