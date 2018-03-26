package com.github.richygreat.as.service;

import org.springframework.stereotype.Service;

@Service
public class SendMoneyService {
	public boolean triggerDebit() {
		System.out.println("SendMoneyService.triggerDebit");
		return true;
	}
}
