package com.github.richygreat.as.event;

public class TransferCreatedEvent {
	private String transactionId;

	public TransferCreatedEvent(String transactionId) {
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
