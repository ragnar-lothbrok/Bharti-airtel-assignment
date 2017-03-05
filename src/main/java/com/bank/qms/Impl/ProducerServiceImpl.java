package com.bank.qms.Impl;

import com.bank.qms.api.ProducerService;
import com.bank.qms.constants.QMSConstants;
import com.bank.qms.model.StandingQueue;
import com.bank.qms.model.Token;

public class ProducerServiceImpl implements ProducerService {

	private ProducerServiceImpl() {

	}

	private static class SingletonHelper {
		private static final ProducerService ProducerService = new ProducerServiceImpl();
	}

	public static ProducerService getProducerServiceInstance() {
		return SingletonHelper.ProducerService;
	}

	public Boolean produce(final StandingQueue standingQueue, Token token) {
		if (token != null && standingQueue != null) {
			while(standingQueue.getBlockingQueue().size() == QMSConstants.MAX_QUEUE_ELEMENT){
				System.out.println("Queue is full wait for some time.");
			}
			standingQueue.getBlockingQueue().add(token);
			System.out.println("Customer added to queue : "+token.getTokenId());
			return true;
		}
		return false;
	}

}
