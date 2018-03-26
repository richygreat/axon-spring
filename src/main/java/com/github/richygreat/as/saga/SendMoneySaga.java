package com.github.richygreat.as.saga;

import static org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.richygreat.as.command.HandleSuspensionCommand;
import com.github.richygreat.as.command.ProcessDebitCommand;
import com.github.richygreat.as.command.ProcessTransferCommand;
import com.github.richygreat.as.command.SendTransferCompleteEmailCommand;
import com.github.richygreat.as.event.TransferCompletedEvent;
import com.github.richygreat.as.event.TransferCreatedEvent;
import com.github.richygreat.as.event.TransferReadyEvent;
import com.github.richygreat.as.service.SendMoneyService;

@Saga
public class SendMoneySaga {
	private transient CommandBus commandBus;

	@Autowired
	private SendMoneyService sendMoneyService;

	@StartSaga
	@SagaEventHandler(associationProperty = "transactionId")
	public void on(TransferCreatedEvent event) {
		System.out.println("Saga Started");
		commandBus.dispatch(asCommandMessage(new ProcessTransferCommand(event.getTransactionId())));
	}

	@SagaEventHandler(associationProperty = "transactionId")
	public void on(TransferReadyEvent event) {
		boolean debitDone = sendMoneyService.triggerDebit();
		if (debitDone) {
			commandBus.dispatch(asCommandMessage(new ProcessDebitCommand(event.getTransactionId())));
			commandBus.dispatch(asCommandMessage(new SendTransferCompleteEmailCommand(event.getTransactionId())));
		} else {
			commandBus.dispatch(asCommandMessage(new HandleSuspensionCommand(event.getTransactionId())));
		}
	}

	@EndSaga
	@SagaEventHandler(associationProperty = "transactionId")
	public void on(TransferCompletedEvent event) {
		System.out.println("Saga Completed");
	}

	@Autowired
	public void setCommandBus(CommandBus commandBus) {
		this.commandBus = commandBus;
	}

	public CommandBus getCommandBus() {
		return commandBus;
	}
}
