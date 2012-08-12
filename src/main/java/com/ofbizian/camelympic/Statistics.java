package com.ofbizian.camelympic;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class Statistics implements Processor {
    private static long tweetCount = 0L;
    private static long imageCount = 0L;

    public void process(Exchange exchange) throws Exception {
        tweetCount++;

        if (exchange.getIn().getBody() instanceof Tweet) {
            imageCount++;
            Tweet tweet = exchange.getIn().getBody(Tweet.class);
            tweet.withCount(tweetCount);
            tweet.withImageCount(imageCount);
        }
    }
}
