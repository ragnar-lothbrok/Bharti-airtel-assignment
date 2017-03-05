package com.bank.qms.model;

import java.util.concurrent.LinkedBlockingQueue;

public class StandingQueue {

	private LinkedBlockingQueue<Token> blockingQueue = new LinkedBlockingQueue<Token>();

	public LinkedBlockingQueue<Token> getBlockingQueue() {
		return blockingQueue;
	}

}
