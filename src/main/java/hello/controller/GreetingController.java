package hello.controller;

import hello.pojo.Greeting;
import hello.pojo.Message;
import hello.service.MessageSaver;
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
    MessageSaver messageSaver;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(Message message, SimpMessageHeaderAccessor headerAccessor) throws Exception {

        HttpHeaders httpHeaders = (HttpHeaders) headerAccessor.getSessionAttributes().get(HTTP_REQUEST_HEADERS);
        messageSaver.saveMessage(message, httpHeaders.get(HttpHeaders.HOST).toString(), LocalDateTime.now());

        return new Greeting("Message: " + message.getMessage() + " // " + httpHeaders);
    }
}
