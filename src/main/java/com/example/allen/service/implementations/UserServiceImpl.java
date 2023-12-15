package com.example.allen.service.implementations;

import com.example.allen.service.interfaces.DealService;
import com.example.allen.service.interfaces.UserService;
import com.example.allen.service.models.Deal;
import com.example.allen.service.models.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    Map<String, User> userMap = new HashMap<>();
    Map<String, List<Deal>> userDealMap = new HashMap<>();

    Long durationOneDay = 24 * 60 * 60 * 1L;

    private final DealService dealService;

    public UserServiceImpl(DealService dealService) {
        this.dealService = dealService;
    }

    public User addUser(String name) {
        String userId = UUID.randomUUID().toString();

        User user = new User(userId, name);

        userMap.put(user.getUserId(), user);

        return user;
    }

    public User getUser(String userId) {
        return userMap.get(userId);
    }

    public void userClaimDeal(String userId, String dealId) {

        User user = userMap.get(userId);
        if(user == null)
            throw new RuntimeException("User not found");

//      User can claim only one deal at a time
        if (userDealMap.containsKey(userId)) {

            List<Deal> userExistingDeals = userDealMap.get(userId);

            Optional<Deal> foundDeal = userExistingDeals.stream().filter(deal -> dealId.equals(deal.getDealId())).findFirst();
            if(foundDeal.isPresent())
                throw new RuntimeException("User already has this deal");
        }

        Deal deal = dealService.getDeal(dealId);

//      User cannot buy deal if deal duration is over
        Integer dealDuration = deal.getDealDuration();
        Long dealStartTime = deal.getDealCreationTime();

        Long dealEndTime = dealStartTime + dealDuration * durationOneDay;

        Long currentTime = System.currentTimeMillis();

        if (currentTime > dealEndTime)
            throw new RuntimeException("Deal time is over");

//      User cannot buy deal if maxItems are over
        Integer dealItems = deal.getDealItems();

        if (dealItems == 0)
            throw new RuntimeException("Deal items are over");

        dealService.updateDeal(dealId, dealItems - 1, null);

        List<Deal> userDeals = userDealMap.getOrDefault(userId, new ArrayList<>());
        userDeals.add(deal);
        userDealMap.put(userId, userDeals);
    }
}
