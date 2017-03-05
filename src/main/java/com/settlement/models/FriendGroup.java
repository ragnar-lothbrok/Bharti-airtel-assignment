package com.settlement.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	private String groupId;

	List<Spend> spends = new ArrayList<Spend>();

	Map<String, Map<String, Double>> friendMap = new HashMap<String, Map<String, Double>>();

	Map<String, Double> friendsInGroup = new HashMap<String, Double>();

	public FriendGroup(String groupId) {
		super();
		this.groupId = groupId;
		System.out.println("Group initialized with id : " + groupId);

	}

	

	public List<Spend> getSpends() {
		List<Spend> spendsList = new ArrayList<Spend>();
		try {
			for(Spend spend : spends){
				spendsList.add((Spend) spend.clone());
			}
		} catch (Exception e) {
			System.out.println("Exception occured while cloning : "+e.getMessage());
		}
		
		return spendsList;
	}

	public Map<String, Map<String, Double>> getFriendMap() {
		return friendMap;
	}

	public Map<String, Double> getFriendsInGroup() {
		return friendsInGroup;
	}

	public String getGroupId() {
		return groupId;
	}

	public void addSpends(String expenceDetails) {
		if (expenceDetails != null && expenceDetails.length() > 0) {
			expenceDetails = expenceDetails.replace(".", "");
			String split[] = expenceDetails.split(" ");
			String paidBy = split[0];
			String paidTo = split[split.length - 1];
			Double amount = Double.parseDouble(split[2]);
			String friends[] = paidTo.split(",");
			Spend spend = new Spend(paidBy, split[split.length - 2]);
			for (String friendTo : friends) {
				spend.getFriends().add(friendTo);
			}
			spend.setAmount(amount);
			spends.add(spend);
		}
	}

//	public void settle() {
//		for (String friend : friendsInGroup.keySet()) {
//			for (String subFriend : friendsInGroup.keySet()) {
//				double firstAmount = 0.0;
//				if (friendMap.get(subFriend) != null && friendMap.get(subFriend).get(friend) != null) {
//					firstAmount = friendMap.get(subFriend).get(friend);
//				}
//				double secondAmount = 0.0;
//				if (friendMap.get(friend) != null && friendMap.get(friend).get(subFriend) != null) {
//					secondAmount = friendMap.get(friend).get(subFriend);
//				}
//				friendsInGroup.put(friend, friendsInGroup.get(friend) + (firstAmount - secondAmount));
//			}
//		}
//		// System.out.println(friendMap);
//		// System.out.println(friendsInGroup);
//	}
//
//	@Deprecated
//	public void oweToOthers() {
//		clearExpence();
//		for (String friend : friendsInGroup.keySet()) {
//			System.out.println("Person " + friend + " will get  " + friendsInGroup.get(friend));
//		}
//	}

//	private void clearExpence() {
//		for (String friend : friendsInGroup.keySet()) {
//			friendsInGroup.put(friend, 0.0);
//		}
//		settle();
//	}

//	private String getMin() {
//		String minFriend = null;
//		Double minAmount = Double.MAX_VALUE;
//		for (String friend : friendsInGroup.keySet()) {
//			if (minFriend == null) {
//				minFriend = friend;
//				minAmount = friendsInGroup.get(friend);
//			} else {
//				if (friendsInGroup.get(friend) < minAmount) {
//					minFriend = friend;
//					minAmount = friendsInGroup.get(friend);
//				}
//			}
//		}
//		return minFriend;
//	}
//
//	private String getMax() {
//		String maxFriend = null;
//		Double maxValue = Double.MIN_VALUE;
//		for (String friend : friendsInGroup.keySet()) {
//			if (maxFriend == null) {
//				maxFriend = friend;
//				maxValue = friendsInGroup.get(friend);
//			} else {
//				if (friendsInGroup.get(friend) > maxValue) {
//					maxFriend = friend;
//					maxValue = friendsInGroup.get(friend);
//				}
//			}
//		}
//		return maxFriend;
//	}
//
//	private static Double min(Double x, Double y) {
//		return (x < y) ? x : y;
//	}
//
//	private void settleingBalance() {
//		String mxCredit = getMax(), mxDebit = getMin();
//
//		if (friendsInGroup.get(mxCredit) == 0.0 && friendsInGroup.get(mxDebit) == 0.0)
//			return;
//
//		Double min = min(-friendsInGroup.get(mxDebit), friendsInGroup.get(mxCredit));
//		friendsInGroup.put(mxCredit, friendsInGroup.get(mxCredit) - min);
//		friendsInGroup.put(mxDebit, friendsInGroup.get(mxDebit) + min);
//		System.out.println("Person " + mxCredit + " owes " + min + " to Person " + mxDebit);
//
//		settleingBalance();
//	}
//
//	@Deprecated
//	public void settleAmount() {
//		clearExpence();
//		System.out.println("\n Printing Settling amount.");
//		settleingBalance();
//		System.out.println("Settle finished.\n");
//	}
//
//	@Deprecated
//	public void showSpendsByExpenceType() {
//		System.out.println("\nPrinting Spends.");
//		for (Spend spend : spends) {
//			System.out.println(spend.toString());
//		}
//		System.out.println("\n");
//	}

}
