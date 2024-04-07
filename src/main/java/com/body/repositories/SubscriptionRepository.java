package com.body.repositories;

import com.body.dto.SubscriptionDto;
import com.body.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, String> {
    @Query("select new com.body.dto.SubscriptionDto(person.firstName, person.lastName, subscription.createdDate) from Subscription subscription join Person person on person.id = subscription.personReceiverId.id where subscription.personSenderId.id = :senderId")
    List<SubscriptionDto> getSubscriptionsByPersonSenderId(String senderId);

    @Query(value = "delete from subscription where subscription.person_sender_id = ?1 and subscription.person_receiver_id = ?2", nativeQuery = true)
    void deleteSubscriptionByPersonSenderAndPersonReceiverId(String personSenderId, String personReceiverId);
}