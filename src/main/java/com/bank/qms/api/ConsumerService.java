package com.bank.qms.api;

import com.bank.qms.model.StandingQueue;

public interface ConsumerService {

	public Boolean consume(final StandingQueue standingQueue);
}
