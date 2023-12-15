package com.example.allen;

import com.example.allen.service.implementations.DealServiceImpl;
import com.example.allen.service.implementations.SellerServiceImpl;
import com.example.allen.service.implementations.UserServiceImpl;
import com.example.allen.service.interfaces.DealService;
import com.example.allen.service.interfaces.SellerService;
import com.example.allen.service.interfaces.UserService;
import com.example.allen.service.models.Deal;
import com.example.allen.service.models.Seller;
import com.example.allen.service.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AllenApplicationTests {

	DealServiceImpl dealService = new DealServiceImpl();

	UserServiceImpl userService = new UserServiceImpl(dealService);
	SellerServiceImpl sellerService = new SellerServiceImpl(dealService);

	@Test
	void contextLoads() {

		Seller seller1 = sellerService.addSeller("Ridhi");
		Seller seller2 = sellerService.addSeller("Simran");

		Deal deal1 = sellerService.createDeal(seller1.getSellerId(), 2, 10); 	// 17Dec2023
		Deal deal2 = sellerService.createDeal(seller2.getSellerId(), 2, 1);// 17Dec2023

		sellerService.updateDeal(deal1.getDealId(), 20, 2);

//		sellerService.endDeal(deal1.getDealId());

		User user1 = userService.addUser("Mohit");
		User user2 = userService.addUser("Rohit");

//		userService.userClaimDeal(user1.getUserId(), deal2.getDealId());
//		userService.userClaimDeal(user2.getUserId(), deal2.getDealId());	// this should fail

		userService.userClaimDeal(user1.getUserId(), deal1.getDealId());
		userService.userClaimDeal(user1.getUserId(), deal1.getDealId());	// user claimed same deal again, should fail

//		deal1.setDealDuration(0);	// set deal creation time to 0days
//		userService.userClaimDeal(user2.getUserId(), deal1.getDealId());	// this should fail
	}

}
