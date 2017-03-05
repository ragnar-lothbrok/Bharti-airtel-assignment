package com.bank.qms.Impl;

import java.util.Calendar;

import com.bank.qms.api.ConsumerService;
import com.bank.qms.model.StandingQueue;
import com.bank.qms.model.Token;

public class ConsumerServiceImpl implements ConsumerService {

	private ConsumerServiceImpl() {

	}

	private static class SingletonHelper {
		private static final ConsumerService ConsumerService = new ConsumerServiceImpl();
	}

	public static ConsumerService getConsumerServiceInstance() {
		return SingletonHelper.ConsumerService;
	}

	public Boolean consume(final StandingQueue standingQueue) {
		if (standingQueue != null && standingQueue.getBlockingQueue().size() > 0) {
			Token token = standingQueue.getBlockingQueue().remove();
			Boolean isExpired = TokenServiceImpl.getTokenServiceInstance().isTokenExpired(token);
			if (isExpired == null || !isExpired) {
				return true;
			} else {
				long time = token.getExpirationTime();
				System.out.println("Customer Served : " + token.getTokenId());
				try {
					Thread.sleep(Calendar.getInstance().getTimeInMillis() - time);
				} catch (InterruptedException e) {
					System.out.println("Exception occured while waiting for customer.");
				}
				return true;
			}
		}
		return false;
	}
}
