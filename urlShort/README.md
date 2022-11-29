##SIMPLE URL SHORTNER
-----------------------

This is a simple URL shortening application like Tiny URL that is build on Java, Spring boot for the back-end and HTML, JavaScript 
for the front-end with H2 database which is a in-memory database. Application uses murmur3 hashing technique this can also
be hashed using MD5 or SHA algorithm.

##Application working steps:
----------------------------

1. This application has a main rest controller which contains 2 services on for coverting the long url to the encoded url and the other 
service is for the redirecting to long url using the encoded url.

2. The service layer of the application contains the functionality to perform the operations and it has the functionality which will compute 
how frequently url is shorten and even how many time it got redirected.

 * To calculate the frequency of url conversion application needs three parameter one is creation date of url, recently accessed time of url
 and total no of conversions. By finding the difference between the dates and then dividing it with total no of conversion will give the answer
 for how frequently it is accessed.
 
 								creation date of url + recently accessed time of url
 								------------------------------------------------------
 											total no of conversions


 * To calculate how many times the url is redirect a variable count is set and recorded each redirect of the long url.
 
 3. A simple html page is built along with the javascript for the event handling which has 2 tabs one for longurl and one for short encoded 
 link with two button for convert and redirect each.
 
##Running the application:
-------------------------

Running using docker file:
---------------------------

1. Clone the project.
2. Move to the directory of project in terminal or CMD.
3. Run docker using the below command for building image and container.
 
 $ docker build -t image .
 $ docker run --name container --network dev-network -p 8081:80 image
 
4.Open localhost:8080/ to see the landing page of the application. In the console we can find the jdbc driver for the H2 database.
5.Open localhost:8080/H2-console and paste the JDBC driver here to access the H2 database


Running on local server:
-------------------------
1.First clone the application from the GIT
2.Open project in your favorite editor and change application.properties file to point to your H2 database
3.Run Spring project as Java application
4.In the console we can find the jdbc driver for the H2 database.
5.Open localhost:8080/ to see the landing page of the application.
6.Open localhost:8080/H2-console and paste the JDBC driver here to access the H2 database



  											


