package com.body.models;

import jakarta.persistence.*;

@Entity
@Table(name = "subscription")
public class Subscription extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "person_sender_id", referencedColumnName = "id")
    private Person personSenderId;

    @ManyToOne
    @JoinColumn(name = "person_receiver_id", referencedColumnName = "id")
    private Person personReceiverId;

    public Person getPersonSenderId() {
        return personSenderId;
    }

    public void setPersonSenderId(Person personSenderId) {
        this.personSenderId = personSenderId;
    }

    public Person getPersonReceiverId() {
        return personReceiverId;
    }

    public void setPersonReceiverId(Person personReceiverId) {
        this.personReceiverId = personReceiverId;
    }
}