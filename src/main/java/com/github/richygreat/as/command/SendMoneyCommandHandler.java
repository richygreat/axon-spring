package com.github.richygreat.as.command;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.richygreat.as.aggregate.SendMoney;

public class SendMoneyCommandHandler {
	private static final Logger logger = LoggerFactory.getLogger(SendMoneyCommandHandler.class);

	private Repository<SendMoney> repository;

	private EventBus eventBus;

	public SendMoneyCommandHandler(Repository<SendMoney> repository, EventBus eventBus) {
		this.repository = repository;
		this.eventBus = eventBus;
	}

	@CommandHandler
	public void handle(ProcessTransferCommand command) {
		logger.info("handle ProcessTransferCommand");
		Aggregate<SendMoney> sendMoneyAggregate = repository.load(command.getTransactionId());
		sendMoneyAggregate.execute(sendMoney -> sendMoney.processTransaction());
	}

	@CommandHandler
	public void handle(SendTransferCompleteEmailCommand command) {
		logger.info("handle SendTransferCompleteEmailCommand");
		Aggregate<SendMoney> sendMoneyAggregate = repository.load(command.getTransactionId());
		sendMoneyAggregate.execute(sendMoney -> sendMoney.sendDebitMail());
	}

	@CommandHandler
	public void handle(ProcessDebitCommand command) {
		logger.info("handle ProcessDebitCommand");
		Aggregate<SendMoney> sendMoneyAggregate = repository.load(command.getTransactionId());
		sendMoneyAggregate.execute(sendMoney -> sendMoney.processDebit());
	}

	@CommandHandler
	public void handle(HandleSuspensionCommand command) {
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
