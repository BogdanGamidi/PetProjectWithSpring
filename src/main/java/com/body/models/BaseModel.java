package com.body.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}