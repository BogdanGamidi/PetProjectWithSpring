package com.body.services;

import com.body.dto.SubscriptionDto;
import com.body.models.Person;
import com.body.models.Subscription;
import com.body.repositories.SubscriptionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {
    private final Logger logger = LogManager.getLogger(getClass());
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(@Autowired SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public SubscriptionRepository getRepository() {
        return subscriptionRepository;
    }

    // admin call
    public List<Subscription> findAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public List<Person> getSubscriptionsToPersonReceiverId(String receiverId) {
        return subscriptionRepository.getSubscriptionsToPersonReceiverId(receiverId);
    }

    // user call
    public List<SubscriptionDto> getSubscriptionsDtoByPersonSenderId(String senderId) {
        List<SubscriptionDto> subscriptionsDto = null;
        try {
            subscriptionsDto = subscriptionRepository.getSubscriptionsDtoByPersonSenderId(senderId);
        } catch(Exception e) {
            logger.error("Person with id {} not found", senderId);
        }
        return subscriptionsDto;
    }

    public Subscription createSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    // admin call
    public void deleteSubscription(String id) {
        subscriptionRepository.deleteById(id);
    }

    // user call
    public Integer deleteSubscriptionByUser(String personSenderId, String personReceiverId) {
        try {
            subscriptionRepository.deleteSubscriptionByPersonSenderAndPersonReceiverId(personSenderId, personReceiverId);

        } catch(Exception e) {
            logger.error("Error deleting subscription between {} and {}", personSenderId, personReceiverId, e);
        }
        return subscriptionRepository.deleteSubscriptionByPersonSenderAndPersonReceiverId(personSenderId, personReceiverId);
    }
}