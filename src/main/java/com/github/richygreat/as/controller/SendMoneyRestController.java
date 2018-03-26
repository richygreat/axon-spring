package com.github.richygreat.as.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.richygreat.as.command.CreateTransferCommand;

@RestController
public class SendMoneyRestController {
	@Autowired
	private CommandGateway commandGateway;

	@RequestMapping("/api/sendmoney")
	public String sendMoney() {
		String transactionId = UUID.randomUUID().toString();
		commandGateway.send(new CreateTransferCommand(transactionId));
		return "success";
	}
}
