# Camelympics

Apache Camel powered app that displays twitter images near real time.

## How to setup
Update app.properties with your [Twitter application keys](https://dev.twitter.com/apps/new) and set the search terms.

## How to run through Maven
Compile and run the app with the following command: mvn
Then visit http://localhost:8080

## How to run through Docker
Build a docker image with the following command: mvn package docker:build
Run a Docker container: mvn docker:start
Then visit http://docker_host:8080
Stop the Docker container: mvn docker:stop

## Read about the app on my [blog](http://www.ofbizian.com/2012/08/olympics-image-loader-powered-by-camel.html)

![A screenshot of the application](http://4.bp.blogspot.com/-w41EsCT_Jow/UCeieWuFVPI/AAAAAAAAAEs/CpUhuCPjrOw/s320/shot.jpg)

## License

(The MIT License)

Copyright (c) 2012 Bilgin Ibryam &lt;bibryam at gmail dot com&gt;

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
'Software'), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
