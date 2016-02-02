package com.ofbizian.camelympics;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import twitter4j.MediaEntity;
import twitter4j.Status;

public class ImageExtractor implements Processor {
    public void process(Exchange exchange) throws Exception {
        Status status = exchange.getIn().getBody(Status.class);
        MediaEntity[] mediaEntities = status.getMediaEntities();
        if (mediaEntities != null && !status.isPossiblySensitive()) {
            for (MediaEntity mediaEntity : mediaEntities) {
                exchange.getIn().setBody(
                        new Tweet()
                                .withName(status.getUser().getScreenName())
                                .withText(status.getText())
                                .withImageUrl(mediaEntity.getMediaURL().toString())
                );

                exchange.getIn().setHeader(CamelympicsRoute.UNIQUE_IMAGE_URL, mediaEntity.getMediaURL().toString());
                break; //grab only the first image
            }
        }
    }
}
