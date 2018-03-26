package com.github.richygreat.as.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class HandleSuspensionCommand {
	@TargetAggregateIdentifier
	private String transactionId;

	public HandleSuspensionCommand(String transactionId) {
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
