package com.charter.reward.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.charter.reward.entity.TransactionDto;
import com.charter.reward.services.RewardPointsService;
import com.charter.reward.utils.DateUtil;

@RestController
@RequestMapping("/rewards")
public class RewardsController {
	
	@Autowired
	private  RewardPointsService rewardPointsService;
	
	@PostMapping("/calculate")
    public Map<String, HashMap<String, Integer>>  calculateRewardPoints(@RequestBody List<TransactionDto> transactions) throws Exception {
        Map<String, HashMap<String, Integer>> result = new HashMap<String, HashMap<String, Integer>>();
        for (TransactionDto transaction : transactions) {
            int points = rewardPointsService.calculateRewardPoints(transaction.getAmount());
            String customerId = String.valueOf(transaction.getCustomerId());
           
            String month = DateUtil.getMonth(transaction.getTransactionDate());
            if (!result.containsKey(customerId)) {
                result.put(customerId, new HashMap<>());
            }
            Map<String, Integer> customerPoints = result.get(customerId);
            if (!customerPoints.containsKey(month)) {
                customerPoints.put(month, 0);
            }
            customerPoints.put(month, customerPoints.get(month) + points);
        }
        return result;
    }

}
