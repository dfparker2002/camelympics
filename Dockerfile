FROM java:8u40
MAINTAINER Bilgin Ibryam
EXPOSE 8080
COPY maven /maven/
CMD java -Djava.security.egd=file:/dev/./urandom -Dfoo=${bilgin} -DconsumerKey=${consumerKey}  -DconsumerSecret=${consumerSecret} -DaccessToken=${accessToken} -DaccessTokenSecret=${accessTokenSecret} -DsearchTerm=${searchTerm} -Dbilgin=${foo} -jar /maven/camelympics.jar
