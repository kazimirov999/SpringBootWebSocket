package hello;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageRouteBuilder extends RouteBuilder {

    private final String MESSAGE_DIRECTORY;

    private static final String QUEUE = "javainuse";

    public MessageRouteBuilder(@Value("${log.message.directory}")String messageDirectory,
                               @Value("${log.message.file.name}") String fileName) {
        String baseUrl = getClass().getClassLoader().getResource(fileName).getFile();
        this.MESSAGE_DIRECTORY = baseUrl.substring(0,baseUrl.lastIndexOf("/"));
    }


    @Override
    public void configure() throws Exception {

        from("file:"+MESSAGE_DIRECTORY+"?noop=true").process(m->)
                .split()
                .tokenize("\n")
                .to("jms:queue:javainuse");
    }
}
