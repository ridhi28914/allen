package com.example.allen.service.implementations;

import com.example.allen.service.interfaces.DealService;
import com.example.allen.service.interfaces.SellerService;
import com.example.allen.service.models.Deal;
import com.example.allen.service.models.Seller;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SellerServiceImpl implements SellerService {

    Map<String, Seller> sellers = new HashMap<>();

    private final DealService dealService;

    public SellerServiceImpl(DealService dealService) {
        this.dealService = dealService;
    }

    public Seller addSeller(String name) {

        String sellerId = UUID.randomUUID().toString();

        Seller seller = new Seller(sellerId, name);

        sellers.put(sellerId, seller);

        return seller;
    }

    public Deal createDeal(String sellerId, Integer dealDuration, Integer dealItems) {
        return dealService.createDeal(dealDuration, dealItems, sellers.get(sellerId));
    }

    public Deal updateDeal(String dealId, Integer dealItems, Integer dealDuration) {
        return dealService.updateDeal(dealId, dealItems, dealDuration);
    }

    public void endDeal(String dealId) {
        Deal deal = dealService.getDeal(dealId);
        dealService.endDeal(deal);
    }
}
