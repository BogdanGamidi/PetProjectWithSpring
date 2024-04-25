package com.body.interceptors;

import com.body.models.Message;
import com.body.models.Person;
import com.body.models.Post;
import com.body.models.Subscription;
import com.body.services.MessageService;
import com.body.services.SubscriptionService;
import org.hibernate.CallbackException;
import org.hibernate.Interceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuditLogInterceptor implements Interceptor {
    private final MessageService messageService;
    private final SubscriptionService subscriptionService;

    @Autowired
    public AuditLogInterceptor(@Lazy MessageService messageService, @Lazy SubscriptionService subscriptionService) {
        this.messageService = messageService;
        this.subscriptionService = subscriptionService;
    }

    @Override
    public boolean onSave(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        if (entity instanceof Subscription) {
            Message message = new Message();
            message.setPersonSenderId(((Subscription) entity).getPersonSenderId());
            message.setPersonReceiverId(((Subscription) entity).getPersonReceiverId());
            message.setContent("You have a new subscription, person id: " + ((Subscription) entity).getPersonSenderId().getId() + " is subscribed to your account");
            messageService.createMessage(message);
        } else if (entity instanceof Post) {
            List<Person> subscriptions = subscriptionService.getSubscriptionsToPersonReceiverId(((Post) entity).getPerson().getId());
            for (Person subscription : subscriptions) {
                Message message = new Message();
                message.setPersonSenderId(((Post) entity).getPerson());
                message.setPersonReceiverId(subscription);
                message.setContent("Person id: " + ((Post) entity).getPerson().getId() + " has created a new post");
                messageService.createMessage(message);
            }
        }
        return false;
    }
}