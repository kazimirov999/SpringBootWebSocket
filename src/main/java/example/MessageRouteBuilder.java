package example;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class MessageRouteBuilder extends RouteBuilder {

    private static final String QUEUE = "javainuse";

    private final String messageDirectory;


    public MessageRouteBuilder(@Value("${log.message.directory}") String messageDirectory,
                               @Value("${log.message.file.name}") String fileName) {
        String baseUrl = getClass().getClassLoader().getResource(fileName).getFile();
        this.messageDirectory = baseUrl.substring(0, baseUrl.lastIndexOf("/"));
    }

    @Override
    public void configure() throws Exception {

        from("file:" + messageDirectory + "?noop=true")
                .split()
                .tokenize("\n")
                .to("jms:queue:" + QUEUE);
    }
}
