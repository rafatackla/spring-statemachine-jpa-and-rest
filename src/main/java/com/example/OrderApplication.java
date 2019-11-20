package com.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import com.example.order.domain.Order;
import com.example.order.enums.OrderEvent;
import com.example.order.enums.OrderState;

@SpringBootApplication
@EntityScan
class OrderApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(OrderApplication.class).build().run(args);
    }

    @Bean
    public DefaultStateMachineAdapter<OrderState, OrderEvent, Order> orderStateMachineAdapter(
            StateMachineFactory<OrderState, OrderEvent> stateMachineFactory,
            StateMachinePersister<OrderState, OrderEvent, Order> persister) {
        return new DefaultStateMachineAdapter<>(stateMachineFactory, persister);
    }
//
//    @Bean
//    public ContextObjectResourceProcessor<OrderState, OrderEvent,Order> orderResourceProcessor(
//            EntityLinks entityLinks,
//            DefaultStateMachineAdapter<OrderState, OrderEvent, ContextEntity<OrderState, OrderEvent, ? extends Serializable>> orderStateMachineAdapter) {
//        return new ContextObjectResourceProcessor<>(entityLinks, orderStateMachineAdapter);
//    }

    @Bean
    public StateMachinePersister<OrderState, OrderEvent, Order> persister(
            StateMachinePersist<OrderState, OrderEvent, Order> persist) {
        return new DefaultStateMachinePersister<>(persist);
    }

    @Bean
    public StateMachinePersist<OrderState, OrderEvent,Order> persist() {
        return new StateMachinePersist<>() {

            @Override
            public StateMachineContext<OrderState, OrderEvent> read(
                   Order order) throws Exception {
                return order.getStateMachine().getStateMachineContext();
            }

            @Override
            public void write(StateMachineContext<OrderState, OrderEvent> context,
                   Order order) throws Exception {
                order.getStateMachine().setStateMachineContext(context);
            }
        };
    }
}
