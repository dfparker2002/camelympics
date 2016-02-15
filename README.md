# Camelympics
A minimalistic Apache Camel application (one Java class with 150 lines) that streams live Twitter photos.

### Screenshot 
Read more about the app on my [blog](http://www.ofbizian.com/2012/08/olympics-image-loader-powered-by-camel.html)

![A screenshot of the application](https://3.bp.blogspot.com/-6HJJ93qqWRo/VraLUqWYx9I/AAAAAAAAD7g/v46Z4IV5OIw/s1600/live_twitter_photo_stream.png)

### How to run with Maven
If you have Maven and Git installed that is the easiest way to run this application.   
Clone the project and update `app.properties` as described below.  
Then compile and run the app with the following command: `mvn clean compile exec:java`   
Then go to *[http://localhost:8080](http://localhost:8080)*  
Or customize the number of previews (4x4 by default) to 10x10 as following: *[http://localhost:8080?rows=10&cols=10](http://localhost:8080?rows=10&cols=10)*  

### How to run with Docker
If you have a docker installed, you can directly poll the docker image from *[Docker Hub](https://hub.docker.com/r/bibryam/camelympics/)* and run the application without compiling.  
If you prefer, you can first pull the docker image: `docker pull bibryam/camelympics:1.0`  
Run the image using your own Twitter keys and preferred search term. For example:  
```
docker run 
-e "consumerKey=83VYApZjhdkKJHDa3qq2dq" 
-e "consumerSecret=M00Lzd5XsHnvnRpips0LSKJDLSKJDLSKJDSApy1GFB9JjNhu" 
-e "accessToken=19341814-3592zsZ1LKAJDLSAKDJVB8Z2FvNweYA0nfHACO" 
-e "accessTokenSecret=ZBk0yIqjaBbWLAKSjdlskjdkLAKohve9wvgZj2XysiTo" 
-e "searchTerm=sport,football,soccer,cricket,hockey,tennis,volleyball,baseball,golf,basketball" 
--rm -p 8080:8080 bibryam/camelympics:1.0
```

Then go to *[http://DOCKER_HOST:8080](http://DOCKER_HOST:8080)*   
Or customize the number of previews (4x4 by default) to 10x10 as following: *[http://localhost:8080?rows=10&cols=10](http://localhost:8080?rows=10&cols=10)*  

### How to run with Maven Docker Plugin
An alternative flow is to build your docker image using Maven plugin. Clone the project, update `app.properties`  and then build docker images through the configured maven plugin: `mvn package docker:build`  
Run the Docker container through maven plugin: `mvn docker:start`  
Stream the logs: `mvn docker:logs -Ddocker.follow`  
Stop the docker container: `mvn docker:stop`  
Then go to *[http://DOCKER_HOST:8080](http://DOCKER_HOST:8080)*   
Or customize the number of previews (4x4 by default) to 10x10 as following: *[http://localhost:8080?rows=10&cols=10](http://localhost:8080?rows=10&cols=10)*  

### How to setup keys (mandatory step if not passing keys at run time)
Generate your own [twitter application keys](https://dev.twitter.com/apps/new). Then update `app.properties` with your keys and the search terms.
```
consumerKey=key
consumerSecret=secret
accessToken=token
accessTokenSecret=tokenSecret
searchTerm=sport,football,soccer,cricket,hockey,tennis,volleyball,baseball,golf,basketball
```
Rather than updating the file, you can also pass those values as environment variables to the JVM.  

### License
Camelympics is licensed under The MIT License.  
100% Free. Camelympics is free to use but attribution is required. This means you must leave footer links and the license info intact. That's all.
