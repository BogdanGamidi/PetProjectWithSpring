package com.body.controllers;

import com.body.models.Subscription;
import com.body.services.SubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/admin")
    public List<Subscription> findAllSubscriptions() {
        return subscriptionService.findAllSubscriptions();
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<Subscription> findSubscriptionById(@PathVariable String id) {
        Optional<Subscription> subscription = subscriptionService.getSubscriptionRepository().findById(id);
        if(subscription.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subscription.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Object>> getSubscriptionsByPersonSender(@PathVariable String id) {
        Optional<List<Object>> subscriptions = Optional.ofNullable(subscriptionService.getSubscriptionsByPersonSender(id));
        if(subscriptions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subscriptions.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription) {
        try {
            return new ResponseEntity<>(subscriptionService.createSubscription(subscription), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> deleteSubscription(@PathVariable String id) {
        try {
            subscriptionService.deleteSubscription(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{personSenderId}/{personReceiverId}")
    public ResponseEntity<String> deleteSubscription(@PathVariable String personSenderId, @PathVariable String personReceiverId) {
        try {
            subscriptionService.getSubscriptionRepository().deleteSubscription(personSenderId, personReceiverId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
