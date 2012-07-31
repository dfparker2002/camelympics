/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ofbizian.camelympic;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.twitter.TwitterComponent;
import org.apache.camel.component.websocket.WebsocketComponent;
import org.apache.camel.processor.idempotent.MemoryIdempotentRepository;

import twitter4j.MediaEntity;
import twitter4j.Status;

public class CamelympicsRoute extends RouteBuilder {
    public static final String UNIQUE_IMAGE_URL = "UNIQUE_IMAGE_URL";
    private String consumerKey;
    private String consumerSecret;
    private String accessToken;
    private String accessTokenSecret;
    private String searchTerm;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setAccessTokenSecret(String accessTokenSecret) {
        this.accessTokenSecret = accessTokenSecret;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public void configure() throws Exception {
        WebsocketComponent wc = getContext().getComponent("websocket", WebsocketComponent.class);
        wc.setPort(9090);
        wc.setStaticResources("classpath:.");

        TwitterComponent tc = getContext().getComponent("twitter", TwitterComponent.class);
        tc.setAccessToken(accessToken);
        tc.setAccessTokenSecret(accessTokenSecret);
        tc.setConsumerKey(consumerKey);
        tc.setConsumerSecret(consumerSecret);

        from("twitter://streaming/filter?type=event&keywords=" + searchTerm)

                .to("log:tweetStream?level=INFO&groupInterval=60000&groupDelay=60000&groupActiveOnly=false")

                .process(new ImageExtractor())

                .filter(header(UNIQUE_IMAGE_URL).isNotNull())

                .idempotentConsumer(header(UNIQUE_IMAGE_URL), MemoryIdempotentRepository.memoryIdempotentRepository(1000))

                .to("log:imageStream?level=INFO&groupInterval=60000&groupDelay=60000&groupActiveOnly=false")

                .throttle(1).timePeriodMillis(5000).asyncDelayed().callerRunsWhenRejected(false)

                .to("websocket:camelympics?sendToAll=true");
    }
}
