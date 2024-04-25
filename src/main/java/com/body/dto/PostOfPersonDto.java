package com.body.dto;

public class PostOfPersonDto {
    private String id;
    private String firstName;
    private String lastName;
    private String content;

    public PostOfPersonDto(String id, String firstName, String lastName, String content) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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