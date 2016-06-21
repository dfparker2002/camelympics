# Camelympics
A minimalistic Apache Camel application (one Java class with 150 lines) that streams live Twitter photos.


### Screenshot 
![A screenshot of the application](https://3.bp.blogspot.com/-6HJJ93qqWRo/VraLUqWYx9I/AAAAAAAAD7g/v46Z4IV5OIw/s1600/live_twitter_photo_stream.png)

Read more about the app on my [blog](http://www.ofbizian.com/search?q=camelympics)


### How to run using Docker
The application is compiled and available at *[Docker Hub](https://hub.docker.com/r/bibryam/camelympics/)*.
Run the image using your own Twitter keys and preferred search term. For example:

    docker run
    -e "consumerKey=83VYApZjhdkKJHDa3qq2dq"
    -e "consumerSecret=M00Lzd5XsHnvnRpips0LSKJDLSKJDLSKJDSApy1GFB9JjNhu"
    -e "accessToken=19341814-3592zsZ1LKAJDLSAKDJVB8Z2FvNweYA0nfHACO"
    -e "accessTokenSecret=ZBk0yIqjaBbWLAKSjdlskjdkLAKohve9wvgZj2XysiTo"
    -e "searchTerm=euro2016,sport"
    --rm -p 8080:8080 bibryam/camelympics:latest

Then go to *[http://DOCKER_HOST:8080](http://DOCKER_HOST:8080)*   
To have a larger number of previews images (which is 4x4 by default) for example 50x7 use following URL params: *[http://DOCKER_HOST:8080?rows=50&cols=7](http://DOCKER_HOST:8080?rows=50&cols=7)*


### How to run locally with Maven
Clone the project and update `app.properties` as described below.
Then compile and run the app with the following command: `mvn clean compile exec:java`
Then go to *[http://localhost:8080](http://localhost:8080)*
To have a larger number of previews images (which is 4x4 by default) for example 50x7 use following URL params: *[http://localhost:8080?rows=50&cols=7](http://localhost:8080?rows=50&cols=7)*


### How to run on Red Hat OpenShift
If you have an *[OpenShift Online account](https://www.openshift.com/)*, use the following commands:


    oc new-project camelympics

    oc login https://OPENSHIFT_HOST --token=YOUR_TOKEN

    oc new-app bibryam/camelympics:latest \
    -e consumerKey="KEY" \ 
    -e consumerSecret="SECRET" \
    -e accessToken="TOKEN" \
    -e accessTokenSecret="TOKEN_SECRET" \
     -e searchTerm="your search term, for example: euro2016"

    oc expose svc camelympics

### Other notes
 - To generate Twitter keys go to [here](https://dev.twitter.com/apps/new). Then update `app.properties` and compile the application, or simply pass the keys as environment variables. Notice that the docker containers on docker hub always expect environment variables and override the `app.properties`.

 - The application can run on any platform that supports docker containers, such as *[Digital Ocean](www.digitalocean.com)* or *[Google Compute Engine](https://cloud.google.com/compute)*.

 - The Camel application has a filter to discard possibly sensitive and NSFW content, but it is not always working as expected with a real time photo stream. Use this at your own risk!!!

### License
Camelympics is licensed under The MIT License.  
100% Free. Camelympics is free to use but attribution is required. This means you must leave footer links and the license info intact. That's all.
