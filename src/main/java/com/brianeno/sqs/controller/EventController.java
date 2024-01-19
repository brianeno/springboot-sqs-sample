package com.brianeno.sqs.controller;

import com.brianeno.sqs.service.EventSenderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/api")
@RestController
public class EventController {

    private final EventSenderService eventSenderService;

    @PostMapping("/event")
    public ResponseEntity<String> sendEvent(@RequestBody final String event) {
        eventSenderService.sendMessage(event);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
