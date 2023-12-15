package com.example.allen.service.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Deal {

    private String dealId;
    private Long dealCreationTime;
    private Integer dealDuration; //num of deals
    private Integer dealItems;
    private Seller dealOwner;
    private Boolean dealActive;

    public Deal(String dealId, Long dealCreationTime, Integer dealDuration, Integer dealItems, Seller dealOwner) {
        this.dealId = dealId;
        this.dealCreationTime = dealCreationTime;
        this.dealDuration = dealDuration;
        this.dealItems = dealItems;
        this.dealOwner = dealOwner;
        this.dealActive = true;
    }
}
