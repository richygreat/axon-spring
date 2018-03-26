package com.github.richygreat.as.command;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.richygreat.as.aggregate.SendMoney;
import com.github.richygreat.as.service.SendMoneyService;

public class SendMoneyCommandHandler {
	private Repository<SendMoney> repository;

	private EventBus eventBus;

	@Autowired
	private SendMoneyService sendMoneyService;

	public SendMoneyCommandHandler(Repository<SendMoney> repository, EventBus eventBus) {
		this.repository = repository;
		this.eventBus = eventBus;
	}

	@CommandHandler
	public void handle(ProcessTransferCommand command) {
		Aggregate<SendMoney> sendMoneyAggregate = repository.load(command.getTransactionId());
		sendMoneyService.triggerDebit();
		/*
		 * Handle service response and then call aggregate function success or failure
		 */
		sendMoneyAggregate.execute(sendMoney -> sendMoney.processedTransaction());
	}

	public Repository<SendMoney> getRepository() {
		return repository;
	}

	public void setRepository(Repository<SendMoney> repository) {
		this.repository = repository;
	}

	public EventBus getEventBus() {
		return eventBus;
	}

	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
	}

}
