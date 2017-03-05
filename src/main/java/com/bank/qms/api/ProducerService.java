package com.bank.qms.api;

import com.bank.qms.model.StandingQueue;
import com.bank.qms.model.Token;

public interface ProducerService {

	public Boolean produce(final StandingQueue standingQueue, Token token);
}
