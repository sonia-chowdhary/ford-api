# ford-casestudy

Run the springboot main class SpringRestClient.java


Token creation call :
curl -X POST \
'http://localhost:8080/oauth/token?grant_type=password&username=Test&password=test123' \
-H 'accept: application/json' \
-H 'authorization: Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0' \
-H 'cache-control: no-cache' \
-H 'postman-token: c300daa9-22e1-43ca-d2c0-26239e742141'

Other Calls :
  For all years : 
    http://localhost:8080/user
  Month wise for an year : 
    http://localhost:8080/user/?year=1972
  Day wise for an year and months : 
    http://localhost:8080/user/?year=1972&month=12&day=12
  For a day : 
    http://localhost:8080/user/?year=1972&month=12&day=12
  Bad request : 
    http://localhost:8080/user/?month=12&day=12
