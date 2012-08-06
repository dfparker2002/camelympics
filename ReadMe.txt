CAMEL OLYMPIC IMAGE LOADER DEMO
========================================

See it running here: http://bit.ly/olympictures

Real time twitter picture loader powered by Apache Camel.
With its 0.2 fps it is enough to keep you up-to-date with the most interesting pictures around the #Olympics -:)
It uses twitter streaming api to receive updates for #Olympics and #London2012,
filters out all tweets which don't contain images,
extract the image info,
remvoe duplicates (based on the image url),
and pushes them the clients using websockets,
by throttling 5 seconds between images.

Read about it at http://www.ofbizian.com/2012/08/olympics-image-loader-powered-by-camel.html

========================================

Running it locally is straightforward:

git clone bla bla
mvn
then visit http://localhost:8080/index.html

