package com.example.order.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Access(AccessType.FIELD)
@Table(name = "orders")
public class Order extends AbstractPersistable<Long> { // NOSONAR

	@Getter
	@Setter
	String nome;

	@Getter
	@Setter
	@OneToOne(cascade = CascadeType.ALL)
	OrderStateMachine stateMachine = new OrderStateMachine();

	@Override
	public boolean isNew() {
		return super.isNew();
	}

}
