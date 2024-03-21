package com.softbank.websocket.controller;

import com.softbank.websocket.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Map;
import java.util.Objects;

@Controller
public class ChatController {

  @MessageMapping("/chat.sendMessage")
  @SendTo("/topic/public")
  @CrossOrigin
  public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
    return chatMessage;
  }

  @MessageMapping("/chat.addUser")
  @SendTo("/topic/public")
  @CrossOrigin
  public ChatMessage addUser(@Payload ChatMessage chatMessage,
                             SimpMessageHeaderAccessor headerAccessor) {
    Map<String, Object> sessionAttributes = Objects.requireNonNull(headerAccessor.getSessionAttributes());
    sessionAttributes.put("username", chatMessage.getSender());
    return chatMessage;
  }

}
