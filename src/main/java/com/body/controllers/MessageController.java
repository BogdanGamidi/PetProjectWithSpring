package com.body.controllers;

import com.body.dto.MessageDto;
import com.body.models.Message;
import com.body.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(@Autowired MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/admin")
    public List<Message> findAllMessages() {
        return messageService.findAllMessages();
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable String id) {
        Optional<Message> message = messageService.getRepository().findById(id);
        if (message.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message.get(), HttpStatus.OK);
    }

    @GetMapping("/{personReceiverId}")
    public ResponseEntity<List<MessageDto>> getMessagesByPersonReceiverId(@PathVariable String personReceiverId) {
        List<MessageDto> listOfMessage = messageService.getMessagesByPersonReceiverId(personReceiverId);
        if (listOfMessage == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listOfMessage, HttpStatus.OK);
    }
}
