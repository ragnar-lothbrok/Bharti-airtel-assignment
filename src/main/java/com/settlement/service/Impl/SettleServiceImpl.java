package com.settlement.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.settlement.models.FriendGroup;
import com.settlement.models.Spend;
import com.settlement.service.api.SettleService;

public class SettleServiceImpl implements SettleService {

	private SettleServiceImpl() {

	}

	private static class SingletonHelper {
		private static final SettleService SettleService = new SettleServiceImpl();
	}

	public static SettleService getSettleService() {
		return SingletonHelper.SettleService;
	}

	public void oweToOthers(FriendGroup friendGroup) {
		Map<String, Map<String, Double>> friendMap = constructAggregateAmountMap(friendGroup);
		Map<String, Double> friendsInGroup = constructTotalFriendsMap(friendGroup);
		settle(friendMap, friendsInGroup);
		for (String friend : friendsInGroup.keySet()) {
			System.out.println("Person " + friend + " will get  " + (-1*friendsInGroup.get(friend)));
		}
	}

	public void settleAmount(FriendGroup friendGroup) {
		System.out.println("\nPrintling settling amount.");
		Map<String, Map<String, Double>> friendMap = constructAggregateAmountMap(friendGroup);
		Map<String, Double> friendsInGroup = constructTotalFriendsMap(friendGroup);
		friendsInGroup = settle(friendMap, friendsInGroup);
		settleingBalance(friendsInGroup);
		System.out.println("\n");
	}

	public void showSpendsByExpenceType(FriendGroup friendGroup) {
		System.out.println("\nPrinting Spends.");
		List<Spend> spends = friendGroup.getSpends();
		if (spends != null) {
			for (Spend spend : spends) {
				System.out.println(spend.toString());
			}
		}
		System.out.println("\n");
	}

	private void settleingBalance(Map<String, Double> friendsInGroup) {
		String mxCredit = getMax(friendsInGroup), mxDebit = getMin(friendsInGroup);

		if (friendsInGroup.get(mxCredit) == 0.0 && friendsInGroup.get(mxDebit) == 0.0)
			return;

		Double min = min(-friendsInGroup.get(mxDebit), friendsInGroup.get(mxCredit));
		friendsInGroup.put(mxCredit, friendsInGroup.get(mxCredit) - min);
		friendsInGroup.put(mxDebit, friendsInGroup.get(mxDebit) + min);
		System.out.println("Person " + mxCredit + " owes " + min + " to Person " + mxDebit);

		settleingBalance(friendsInGroup);
	}

	private String getMin(Map<String, Double> friendsInGroup) {
		String minFriend = null;
		Double minAmount = Double.MAX_VALUE;
		for (String friend : friendsInGroup.keySet()) {
			if (minFriend == null) {
				minFriend = friend;
				minAmount = friendsInGroup.get(friend);
			} else {
				if (friendsInGroup.get(friend) < minAmount) {
					minFriend = friend;
					minAmount = friendsInGroup.get(friend);
				}
			}
		}
		return minFriend;
	}

	private String getMax(Map<String, Double> friendsInGroup) {
		String maxFriend = null;
		Double maxValue = Double.MIN_VALUE;
		for (String friend : friendsInGroup.keySet()) {
			if (maxFriend == null) {
				maxFriend = friend;
				maxValue = friendsInGroup.get(friend);
			} else {
				if (friendsInGroup.get(friend) > maxValue) {
					maxFriend = friend;
					maxValue = friendsInGroup.get(friend);
				}
			}
		}
		return maxFriend;
	}

	private static Double min(Double x, Double y) {
		return (x < y) ? x : y;
	}

	private Map<String, Double> constructTotalFriendsMap(FriendGroup friendGroup) {
		Map<String, Double> friendsInGroup = new HashMap<String, Double>();
		List<Spend> spends = friendGroup.getSpends();
		if (spends != null) {
			for (Spend spend : spends) {
				friendsInGroup.put(spend.getFriend(), 0.0);
				for (String friendTo : spend.getFriends()) {
					friendsInGroup.put(friendTo, 0.0);
				}
			}
		}
		return friendsInGroup;
	}

	private Map<String, Double> settle(Map<String, Map<String, Double>> friendMap, Map<String, Double> friendsInGroup) {
		for (String friend : friendsInGroup.keySet()) {
			for (String subFriend : friendsInGroup.keySet()) {
				double firstAmount = 0.0;
				if (friendMap.get(subFriend) != null && friendMap.get(subFriend).get(friend) != null) {
					firstAmount = friendMap.get(subFriend).get(friend);
				}
				double secondAmount = 0.0;
				if (friendMap.get(friend) != null && friendMap.get(friend).get(subFriend) != null) {
					secondAmount = friendMap.get(friend).get(subFriend);
				}
				friendsInGroup.put(friend, friendsInGroup.get(friend) + (firstAmount - secondAmount));
			}
		}
		return friendsInGroup;
	}

	private Map<String, Map<String, Double>> constructAggregateAmountMap(FriendGroup friendGroup) {
		Map<String, Map<String, Double>> friendMap = new HashMap<String, Map<String, Double>>();
		List<Spend> spends = friendGroup.getSpends();
		if (spends != null) {
			for (Spend spend : spends) {
				Double amount = spend.getAmount();
				if (friendMap.get(spend.getFriend()) == null) {
					friendMap.put(spend.getFriend(), new HashMap<String, Double>());
				}
				for (String friendTo : spend.getFriends()) {
					if (friendTo.equalsIgnoreCase(spend.getFriend())) {
						continue;
					}
					if (friendMap.get(spend.getFriend()).get(friendTo) == null) {
						friendMap.get(spend.getFriend()).put(friendTo, (amount * 1.0 / spend.getFriends().size()));
					} else {
						friendMap.get(spend.getFriend()).put(friendTo, friendMap.get(spend.getFriend()).get(friendTo)
								+ (amount * 1.0 / spend.getFriends().size()));
					}
				}
			}
		}
		return friendMap;
	}
	
	private void owesToEachOther(Map<String, Map<String, Double>> friendMap) {
		if(friendMap != null){
			for(String first : friendMap.keySet()){
				for	(String friend : friendMap.get(first).keySet()){
					
				}
			}
		}
	}

}
