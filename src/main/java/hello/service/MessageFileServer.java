package hello.service;

import hello.pojo.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

import static org.apache.commons.lang3.CharEncoding.UTF_8;

@Service
public class MessageFileServer implements MessageSaver {

    @Value("${log.message.directory}")
    private String messageDirectory;


    @Override
    public void saveMessage(Message message) throws IOException {
        writeMessageAsFile(message.getMessage());
    }

    @Override
    public void saveMessage(Message message, String additionalContent) throws IOException {
        writeMessageAsFile(message.getMessage() + " --> " + additionalContent);
    }

    @Override
    public void saveMessage(Message message, String additionalContent, LocalDateTime dateTime) throws Exception {
        writeMessageAsFile(dateTime.toString() + "  :  " + message.getMessage() + " --> " + additionalContent);
    }

    private void writeMessageAsFile(String content) throws IOException {
        Path path = createFile(messageDirectory + "/message: " + LocalDateTime.now());

        Files.write(path, (content + System.lineSeparator()).getBytes(UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

    }

    private Path createFile(String fileName) throws IOException {
        return Files.createFile(Paths.get(fileName));
    }


}
