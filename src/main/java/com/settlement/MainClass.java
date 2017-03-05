package com.settlement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

import com.settlement.models.FriendGroup;
import com.settlement.service.Impl.SettleServiceImpl;
import com.settlement.service.api.SettleService;

public class MainClass {

	public static void main(String[] args) {

		// String arr[] = {"A spends 300 on C,D","B spends 200 on A,C","C spends
		// 300 on A,B","D spends 250 on B"};
		String arr[] = { "A spends 300 on lunch for A,B,C", "A spends 200 on movie for B,C",
				"D spends 600 on dinner for A,B,C,D", "B spends 500 on games for B,C" };

		FriendGroup friendGroup = new com.settlement.models.FriendGroup(UUID.randomUUID().toString());

		for (String expence : arr) {
			friendGroup.addSpends(expence);
		}

		// friendGroup.oweToOthers();
		// friendGroup.settleAmount();
		// friendGroup.showSpendsByExpenceType();

		System.out.println("##### Using service ");
		final SettleService settleService = SettleServiceImpl.getSettleService();
		settleService.oweToOthers(friendGroup);
		settleService.settleAmount(friendGroup);
		settleService.showSpendsByExpenceType(friendGroup);

		askInput();
	}

	private static void askInput() {
		java.io.BufferedReader in = null;
		try {
			final SettleService settleService = SettleServiceImpl.getSettleService();
			int choice = 1;
			in = new BufferedReader(new InputStreamReader(System.in));
			FriendGroup friendGroup = new com.settlement.models.FriendGroup(UUID.randomUUID().toString());
			do {
				System.out.println("\n 1.Add Expence 2.Settle 3.Spend Details 4. He owes 6.Exit \nEnter your choice : ");
				choice = Integer.parseInt(in.readLine());
				if (choice == 1) {
					System.out.println("Enter expence : ");
					String line = in.readLine();
					friendGroup.addSpends(line);
					System.out.println("####################################################");
				} else if (choice == 2) {
					settleService.settleAmount(friendGroup);
					System.out.println("####################################################");
				} else if (choice == 6) {
					System.out.println("##############FINISHED#################");
					break;
				} else if (choice == 3) {
					settleService.showSpendsByExpenceType(friendGroup);
					System.out.println("####################################################");
				}else if (choice == 4) {
					settleService.oweToOthers(friendGroup);
					System.out.println("####################################################");
				}
			} while (choice != -1);
		} catch (Exception e) {
			System.out.println("Exception occured while parsing input : " + e.getMessage());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					System.out.println("Exception ocured while closing stream : " + e.getMessage());
				}
			}
		}

	}
}
