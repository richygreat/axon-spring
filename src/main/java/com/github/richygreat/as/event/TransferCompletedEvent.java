package com.github.richygreat.as.event;

public class TransferCompletedEvent {
	private String transactionId;

	public TransferCompletedEvent(String transactionId) {
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
