package com.example.allen.service.interfaces;

import com.example.allen.service.models.Deal;
import com.example.allen.service.models.Seller;

public interface SellerService {

    Seller addSeller(String name);

    Deal createDeal(String sellerId, Integer dealDuration, Integer dealItems);

    Deal updateDeal(String dealId, Integer dealItems, Integer dealDuration);

    void endDeal(String dealId);
}
