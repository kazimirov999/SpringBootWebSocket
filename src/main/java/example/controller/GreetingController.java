package example.controller;

import example.pojo.Greeting;
import example.pojo.Message;
import example.service.MessageSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

import static javax.xml.ws.handler.MessageContext.HTTP_REQUEST_HEADERS;


@Controller
public class GreetingController {

    @Autowired
    private MessageSaver messageSaver;

    @MessageMapping("/example")
    @SendTo("/topic/greetings")
    public Greeting greeting(Message message, SimpMessageHeaderAccessor headerAccessor) throws Exception {

        HttpHeaders httpHeaders = (HttpHeaders) headerAccessor.getSessionAttributes().get(HTTP_REQUEST_HEADERS);
        messageSaver.saveMessage(message, httpHeaders.get(HttpHeaders.HOST).toString(), LocalDateTime.now());

        return new Greeting("Message: " + message.getMessage() + " // " + httpHeaders);
    }
}
