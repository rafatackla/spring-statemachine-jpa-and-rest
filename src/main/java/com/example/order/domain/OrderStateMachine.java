package com.example.order.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.statemachine.StateMachineContext;

import com.example.ContextEntity;
import com.example.order.enums.OrderEvent;
import com.example.order.enums.OrderState;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "state_machine")
public class OrderStateMachine extends AbstractPersistable<Long>
		implements ContextEntity<OrderState, OrderEvent, Long> {

	@JsonIgnore
	@Getter
	StateMachineContext<OrderState, OrderEvent> stateMachineContext;

	@JsonIgnore
	@Override
	public boolean isNew() {
		return super.isNew();
	}

	@Getter @Setter
	@JsonIgnore
	@Enumerated(EnumType.STRING)
	OrderState currentState;

	
	public void setStateMachineContext(StateMachineContext<OrderState, OrderEvent> stateMachineContext) {
		this.stateMachineContext = stateMachineContext;
		this.currentState = stateMachineContext.getState();
	}

}