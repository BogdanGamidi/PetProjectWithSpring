package com.body.dto;

import java.time.LocalDateTime;

public class SubscriptionDto {
    private String firstName;
    private String lastName;
    private LocalDateTime subscriptionDate;

    public SubscriptionDto(String firstName, String lastName, LocalDateTime subscriptionDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subscriptionDate = subscriptionDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(LocalDateTime subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
