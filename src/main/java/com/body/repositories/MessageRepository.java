package com.body.repositories;

import com.body.dto.MessageDto;
import com.body.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
    @Query(value = "select new com.body.dto.MessageDto(message.createdDate, message.personSenderId.firstName, message.personSenderId.lastName, message.content) from Message message where message.personReceiverId.id = :personReceiverId order by message.createdDate desc")
    List<MessageDto> getMessagesByPersonReceiverId(String personReceiverId);
}