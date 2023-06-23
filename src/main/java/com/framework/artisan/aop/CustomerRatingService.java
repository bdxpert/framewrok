package com.framework.artisan.aop;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class CustomerRatingService {
    @Async
    @EventListener
    public void onEvent(NewCustomerEvent event) {
        System.out.println("CustomerRatingService::");
        System.out.println("received event :" + event.getCustomer().getName());;
    }
}
