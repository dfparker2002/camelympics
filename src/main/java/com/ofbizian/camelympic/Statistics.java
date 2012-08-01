package com.ofbizian.camelympic;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class Statistics implements Processor {
    private static long startDate = new Date().getTime();
    private static long tweetCount = 0;
    private static long imageCount = 0;

    public void process(Exchange exchange) throws Exception {
        tweetCount++;

        if (exchange.getIn().getBody() instanceof Tweet) {
            imageCount++;
            Tweet tweet = exchange.getIn().getBody(Tweet.class);
            tweet.withCount(tweetCount);
            tweet.withImageCount(imageCount);
            tweet.withStartDate(startDate);
        }
    }
}
