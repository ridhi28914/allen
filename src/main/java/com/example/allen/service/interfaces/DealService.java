package com.example.allen.service.interfaces;

import com.example.allen.service.models.Deal;
import com.example.allen.service.models.Seller;

public interface DealService {

    Deal createDeal(Integer dealDuration, Integer dealItems, Seller seller);

    void endDeal(Deal deal);

    Deal updateDeal(String dealId, Integer dealItems, Integer dealDuration);

    Deal getDeal(String dealId);
}
