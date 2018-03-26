package com.github.richygreat.as.event;

public class TransferReadyEvent {
	private String transactionId;

	public TransferReadyEvent(String transactionId) {
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
