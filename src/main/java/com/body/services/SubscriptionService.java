package com.body.services;

import com.body.models.Subscription;
import com.body.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(@Autowired SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public SubscriptionRepository getSubscriptionRepository() {
        return subscriptionRepository;
    }

    // admin call
    public List<Subscription> findAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    // user call
    public List<Object> getSubscriptionsByPersonSender(String id) {
        return subscriptionRepository.getSubscriptionsByPersonSender(id);
    }

    public Subscription createSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    // admin call
    public void deleteSubscription(String id) {
        subscriptionRepository.deleteById(id);
    }

    // user call
    public void deleteSubscriptionByUser(String personSenderId, String personReceiverId) {
        subscriptionRepository.deleteSubscription(personSenderId, personReceiverId);
    }
}
