package com.body.models;

import com.body.interceptors.AuditLogInterceptor;
import jakarta.persistence.*;

@Entity
@EntityListeners(AuditLogInterceptor.class)
@Table(name = "post")
public class Post extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Column(name = "content", nullable = false)
    private String content;

    public Post() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}