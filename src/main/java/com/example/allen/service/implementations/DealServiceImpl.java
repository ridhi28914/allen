package com.example.allen.service.implementations;

import com.example.allen.service.interfaces.DealService;
import com.example.allen.service.models.Deal;
import com.example.allen.service.models.Seller;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class DealServiceImpl implements DealService {

    Map<String, Deal> deals = new HashMap<>();

    @Override
    public Deal createDeal(Integer dealDuration, Integer dealItems, Seller seller) {
        String dealId = UUID.randomUUID().toString();

        Deal deal = new Deal(dealId, System.currentTimeMillis(), dealDuration, dealItems, seller);

        deals.put(deal.getDealId(), deal);

        return deal;
    }

    @Override
    public void endDeal(Deal deal) {
        deal.setDealActive(false);
    }

    @Override
    public Deal updateDeal(String dealId, @Nullable Integer dealItems, @Nullable Integer dealDuration) {

        Deal deal = deals.get(dealId);
        if(dealDuration != null)
            deal.setDealDuration(dealDuration);

        if(dealItems != null)
            deal.setDealItems(dealItems);

        return deal;
    }

    @Override
    public Deal getDeal(String dealId) {

        Deal deal = deals.get(dealId);

        if( deal != null && deal.getDealActive()) {
            return deal;
        }
        else {
            throw new RuntimeException("Deal is not found or not active");
        }
    }
}
