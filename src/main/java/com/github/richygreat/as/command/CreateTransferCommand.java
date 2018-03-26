package com.github.richygreat.as.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class CreateTransferCommand {
	@TargetAggregateIdentifier
	private String transactionId;

	public CreateTransferCommand(String transactionId) {
		super();
		this.transactionId = transactionId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}
