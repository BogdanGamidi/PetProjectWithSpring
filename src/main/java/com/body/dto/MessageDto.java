package com.body.dto;

import java.time.LocalDateTime;

public class MessageDto {
    private LocalDateTime messageDate;
    private String firstName;
    private String lastName;
    private String content;

    public MessageDto(LocalDateTime messageDate, String firstName, String lastName, String content) {
        this.messageDate = messageDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.content = content;
    }

    public LocalDateTime getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(LocalDateTime messageDate) {
        this.messageDate = messageDate;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
