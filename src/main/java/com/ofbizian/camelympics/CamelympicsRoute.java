package com.ofbizian.camelympics;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.processor.idempotent.MemoryIdempotentRepository;

public class CamelympicsRoute extends RouteBuilder {
    public static final String UNIQUE_IMAGE_URL = "UNIQUE_IMAGE_URL";

    @Override
    public void configure() throws Exception {
        PropertiesComponent propertiesComponent = new PropertiesComponent();
        propertiesComponent.setLocation("classpath:app.properties");
        getContext().addComponent("properties", propertiesComponent);

        ImageExtractor imageExtractor = new ImageExtractor();
        Statistics statistics = new Statistics();

        from("twitter://streaming/filter?type=event&keywords={{twitter.searchTerm}}&accessToken={{twitter.accessToken}}&accessTokenSecret={{twitter.accessTokenSecret}}&consumerKey={{twitter.consumerKey}}&consumerSecret={{twitter.consumerSecret}}")

                .to("log:tweetStream?level=INFO&groupInterval=60000&groupDelay=60000&groupActiveOnly=false")

                .process(imageExtractor)

                .process(statistics)

                .filter(body().isInstanceOf(Tweet.class))

                .idempotentConsumer(header(UNIQUE_IMAGE_URL), MemoryIdempotentRepository.memoryIdempotentRepository(10000))

                .to("log:imageStream?level=INFO&groupInterval=60000&groupDelay=60000&groupActiveOnly=false")

                .marshal().json(JsonLibrary.Jackson)

                .convertBodyTo(String.class)

                .to("websocket://localhost:8080/camelympics?sendToAll=true&staticResources=classpath:web/.");

    }
}
