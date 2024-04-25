package com.body.services;

import com.body.dto.MessageDto;
import com.body.models.Message;
import com.body.repositories.MessageRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final Logger logger = LogManager.getLogger(getClass());
    private final MessageRepository messageRepository;

    public MessageService(@Autowired MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public MessageRepository getRepository() {
        return messageRepository;
    }

    public List<Message> findAllMessages() {
        return messageRepository.findAll();
    }

    public List<MessageDto> getMessagesByPersonReceiverId(String personReceiverId) {
        List<MessageDto> messageDto = null;
        try {
            messageDto = messageRepository.getMessagesByPersonReceiverId(personReceiverId);
        } catch (Exception e) {
            logger.error("Person with id {} not found", personReceiverId);
        }
        return messageDto;
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }
}
