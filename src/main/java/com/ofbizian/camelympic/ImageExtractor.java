package com.ofbizian.camelympic;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import twitter4j.MediaEntity;
import twitter4j.Status;

public class ImageExtractor implements Processor {
    public void process(Exchange exchange) throws Exception {
        Status status = exchange.getIn().getBody(Status.class);
        MediaEntity[] mediaEntities = status.getMediaEntities();
        if (mediaEntities != null) {
            for (MediaEntity mediaEntity : mediaEntities) {
                exchange.getIn().setBody(
                        status.getUser().getScreenName() + ";" +
                                status.getText() + ";" +
                                mediaEntity.getMediaURL().toString() + ";" +
                                mediaEntity.getSizes().get(3).getWidth() + ";" +
                                mediaEntity.getSizes().get(3).getHeight());

                exchange.getIn()
                        .setHeader(CamelympicsRoute.UNIQUE_IMAGE_URL, mediaEntity.getMediaURL().toString());
                break; //grab only the first image
            }
        }
    }
}
