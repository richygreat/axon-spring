package com.github.richygreat.as.aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import com.github.richygreat.as.command.CreateTransferCommand;
import com.github.richygreat.as.event.TransferCompletedEvent;
import com.github.richygreat.as.event.TransferCreatedEvent;
import com.github.richygreat.as.event.TransferReadyEvent;

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
		apply(new TransferReadyEvent(id));
	}

	public void sendDebitMail() {
		/* Set debitMailSent status as true */
	}

	public void processDebit() {
		apply(new TransferCompletedEvent(id));
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
