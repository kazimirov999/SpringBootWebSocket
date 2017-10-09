package example.service;

import example.pojo.Message;

import java.time.LocalDateTime;

public interface MessageSaver {

    void saveMessage(Message message) throws Exception;

    void saveMessage(Message message, String additionalContent) throws Exception;

    void saveMessage(Message message, String additionalContent, LocalDateTime date) throws Exception;
}
