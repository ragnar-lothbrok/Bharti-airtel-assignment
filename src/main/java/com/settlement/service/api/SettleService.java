package com.settlement.service.api;

import com.settlement.models.FriendGroup;

public interface SettleService {

	void oweToOthers(FriendGroup friendGroup);

	void settleAmount(FriendGroup friendGroup);

	void showSpendsByExpenceType(FriendGroup friendGroup);
}
