package com.github.richygreat.as.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class ProcessDebitCommand {
	@TargetAggregateIdentifier
	private String transactionId;

	public ProcessDebitCommand(String transactionId) {
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
