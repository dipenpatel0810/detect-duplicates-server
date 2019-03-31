# detect-duplicates-server

This Spring Boot server is deployed to Heroku.
We will be sending data to the front-end.

I have used RESTful API.
With GetMapping, the data would be fetched by the front-end.

The URL is :
https://detect-duplicates-server.herokuapp.com/

For the Duplicate data in normal.csv file, go to
https://detect-duplicates-server.herokuapp.com/api/normal/duplicate

For the Unique data in normal.csv file, go to
https://detect-duplicates-server.herokuapp.com/api/normal/unique

For the Duplicate data in advanced.csv file, go to
https://detect-duplicates-server.herokuapp.com/api/advanced/duplicate

For the Unique data in advanced.csv file, go to
https://detect-duplicates-server.herokuapp.com/api/advanced/unique

Apart from this I used 3 external dependencies 
in order to use Metaphone, Levenshtein Distance and parsing 
data from CSV file whose column contains , value.

1) apache commons-csv
2) apache commons-text
3) apache commons-codec

I have added the dependency in the pom.xml file.
So the dependency would be automatically loaded while 
setting up the maven project.
