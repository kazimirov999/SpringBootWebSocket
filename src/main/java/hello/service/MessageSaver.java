package hello.service;

import hello.pojo.Message;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

public interface MessageSaver {

    void saveMessage(Message message) throws Exception;

    void saveMessage(Message message, String additionalContent) throws Exception;

    void saveMessage(Message message, String additionalContent, LocalDateTime date) throws Exception;
}
