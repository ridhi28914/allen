package com.example.allen.service.interfaces;

import com.example.allen.service.models.User;

public interface UserService {

    User addUser(String name);

    User getUser(String userId);

    void userClaimDeal(String userId, String dealId);
}
