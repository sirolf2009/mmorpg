package com.sirolf2009.mmorpg;

import static com.sirolf2009.mmorpg.component.RenovateSubscription.*;

public class Util {

	public static double getPriceForSubscription(int amount) {
		if(amount == 12) {
			return 35;
		} else if(amount == 3) {
			return 10;
		} else if(amount == 2) {
			return 8;
		} else if(amount == 1) {
			return 5;
		} else {
			return 0;
		}
	}

	public static double getPriceForSlots(int amount) {
		return amount;
	}

	public static int getMonthAmountFromDescription(String description) {
		if(description == null || description.isEmpty()) {
			return 0;
		}else if(description.equals(MONTH_1)) {
			return 1;
		} else if(description.equals(MONTH_2)) {
			return 2;
		} else if(description.equals(MONTH_3)) {
			return 3;
		} else if(description.equals(YEAR)) {
			return 12;
		} else {
			return 0;
		}
	}

}
