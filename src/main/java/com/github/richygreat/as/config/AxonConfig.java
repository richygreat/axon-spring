/*
 * Copyright (c) 2016. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.richygreat.as.config;

import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.config.SagaConfiguration;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.richygreat.as.aggregate.SendMoney;
import com.github.richygreat.as.command.SendMoneyCommandHandler;
import com.github.richygreat.as.saga.SendMoneySaga;

@Configuration
public class AxonConfig {

	@Autowired
	private AxonConfiguration axonConfiguration;

	@Autowired
	private EventBus eventBus;

	@Bean
	public SendMoneyCommandHandler bankAccountCommandHandler() {
		return new SendMoneyCommandHandler(axonConfiguration.repository(SendMoney.class), eventBus);
	}

	@Bean
	public SagaConfiguration<SendMoneySaga> sendMoneySagaConfiguration() {
		return SagaConfiguration.trackingSagaManager(SendMoneySaga.class);
	}

	@Autowired
	public void configure(@Qualifier("localSegment") SimpleCommandBus simpleCommandBus) {
		simpleCommandBus.registerDispatchInterceptor(new BeanValidationInterceptor<>());
	}
}
