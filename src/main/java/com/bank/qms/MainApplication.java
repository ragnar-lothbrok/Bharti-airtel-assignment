package com.bank.qms;

import java.util.UUID;

import com.bank.qms.Impl.ConsumerServiceImpl;
import com.bank.qms.Impl.ProducerServiceImpl;
import com.bank.qms.Impl.TokenServiceImpl;
import com.bank.qms.model.StandingQueue;

public class MainApplication {

	public static void main(String[] args) {

		final StandingQueue standingQueue = new StandingQueue();

		int choice = 1;
		
		Thread consumerThread = new Thread(new Runnable() {
			public void run() {
				while(true){
					ConsumerServiceImpl.getConsumerServiceInstance().consume(standingQueue);
				}
			}
		});
		consumerThread.start();

		do {
			switch (choice) {
			case 1:
				System.out.println("Adding customer to queue.");
				ProducerServiceImpl.getProducerServiceInstance().produce(standingQueue, TokenServiceImpl.getTokenServiceInstance().genrateToken(UUID.randomUUID().toString()));
				break;
			case 3:
				System.out.println("Please enter -1 to discontinue.");
				break;
			}

		} while (choice != -1);
		
		if(choice == -1){
			try {
				consumerThread.interrupt();
			} catch (Exception e) {
				System.out.println("Exception occured while closing thread.");
			}
			
		}

	}

}
