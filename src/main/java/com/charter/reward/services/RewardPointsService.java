package com.charter.reward.services;

import org.springframework.stereotype.Service;

@Service
public class RewardPointsService {
	
	public int calculateRewardPoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += (int) ((amount - 100) * 2);
            points = points + 50;
        }
        else if (amount > 50 && amount <= 100) {
            points += (int) ((Math.min(amount, 100) - 50));
        }
        return points;
    }

}
