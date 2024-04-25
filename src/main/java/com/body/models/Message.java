package com.body.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "message")
public class Message extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "person_sender_id", referencedColumnName = "id")
    private Person personSenderId;

    @ManyToOne
    @JoinColumn(name = "person_receiver_id", referencedColumnName = "id")
    private Person personReceiverId;

    @Column(name = "content")
    private String content;

    public Message() {
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}