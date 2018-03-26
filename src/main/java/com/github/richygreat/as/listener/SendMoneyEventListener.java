package com.github.richygreat.as.listener;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.github.richygreat.as.event.TransferCompletedEvent;
import com.github.richygreat.as.event.TransferCreatedEvent;

@Component
public class SendMoneyEventListener {
	@EventHandler
	public void on(TransferCreatedEvent event) {
		System.out.println("TransferCreatedEvent");
	}

	@EventHandler
	public void on(TransferCompletedEvent event) {
		System.out.println("TransferCompletedEvent");
	}
}
