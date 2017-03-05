package com.settlement.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Spend implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private String paidBy;
	private String expences;
	private List<String> friends = new ArrayList<String>();
	private Double amount = 0.0;

	public String getFriend() {
		return paidBy;
	}

	public List<String> getFriends() {
		return friends;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setFriend(String friend) {
		this.paidBy = friend;
	}

	public String getExpences() {
		return expences;
	}

	public void setExpences(String expences) {
		this.expences = expences;
	}

	public Spend(String friend, String expences) {
		super();
		this.paidBy = friend;
		this.expences = expences;
	}

	@Override
	public String toString() {
		return paidBy + " Spends " + amount + " on " + expences + " for " + friends;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Spend clonedSpend = new Spend(paidBy, expences);
		clonedSpend.setAmount(amount);
		clonedSpend.friends = new ArrayList<String>(this.friends);
		return clonedSpend;
	}

}
