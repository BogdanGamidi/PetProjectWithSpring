package com.body.repositories;

import com.body.dto.SubscriptionDto;
import com.body.models.Person;
import com.body.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, String> {
    @Query(value = "select new com.body.dto.SubscriptionDto(person.firstName, person.lastName, subscription.createdDate) from Subscription subscription join Person person on person.id = subscription.personReceiverId.id where subscription.personSenderId.id = :senderId")
    List<SubscriptionDto> getSubscriptionsDtoByPersonSenderId(String senderId);

    @Query(value = "select subscription.personSenderId from Subscription subscription join Person person on person.id = subscription.personSenderId.id where subscription.personReceiverId.id = ?1")
    List<Person> getSubscriptionsToPersonReceiverId(String personReceiverId);

    @Modifying
    @Transactional
    @Query(value = "delete from Subscription where personSenderId.id = :personSenderId and personReceiverId.id = :personReceiverId")
    Integer deleteSubscriptionByPersonSenderAndPersonReceiverId(String personSenderId, String personReceiverId);
}