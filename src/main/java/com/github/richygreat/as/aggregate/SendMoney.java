package com.github.richygreat.as.aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import com.github.richygreat.as.command.CreateTransferCommand;
import com.github.richygreat.as.event.TransferCompletedEvent;
import com.github.richygreat.as.event.TransferCreatedEvent;

@Aggregate
public class SendMoney {
	@AggregateIdentifier
	private String id;

	protected SendMoney() {
	}

	@CommandHandler
	public SendMoney(CreateTransferCommand command) {
		apply(new TransferCreatedEvent(command.getTransactionId()));
	}

	@EventHandler
	public void on(TransferCreatedEvent event) {
		id = event.getTransactionId();
	}

	public void processTransaction() {
		/* Business Logic of calling Rest Api and decide which event based on result */
		apply(new TransferCompletedEvent(id));
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
