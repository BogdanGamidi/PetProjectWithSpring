package com.body.repositories;

import com.body.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, String> {
    @Query(value = "select person.first_name, person.last_name, subscription.created_date from subscription join person on person.id = subscription.person_receiver_id where subscription.person_sender_id = ?1", nativeQuery = true)
    List<Object> getSubscriptionsByPersonSender(String personSenderId);

    @Query(value = "delete from subscription where subscription.person_sender_id = ?1 and subscription.person_receiver_id = ?2", nativeQuery = true)
    void deleteSubscription(String personSenderId, String personReceiverId);
}
