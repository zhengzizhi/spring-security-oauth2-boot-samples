[root@contoso ~]# curl -i localhost:8080/oauth/token -d "grant_type=password&scope=read write&username=jack&password=abc123" -u foo:bar
HTTP/1.1 200 
Cache-Control: no-store
Pragma: no-cache
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
X-Frame-Options: DENY
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Wed, 15 Aug 2018 12:38:51 GMT

{"access_token":"c301463f-73ac-4543-b062-a0a39f51eafb","token_type":"bearer","refresh_token":"454f90ec-0de6-4a60-a3b1-bf598c18f175","expires_in":43199,"scope":"read write"}

[root@contoso ~]# curl -i -H "Authorization: bearer c301463f-73ac-4543-b062-a0a39f51eafb" localhost:8080/flights/1
HTTP/1.1 200 
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Wed, 15 Aug 2018 12:39:25 GMT

{"id":1,"origin":"London","destination":"Paris","airline":"British Airways","flightNumber":"405","date":null,"traveler":"Jack Chen"}

[root@contoso ~]# curl -i -H "Authorization: bearer c301463f-73ac-4543-b062-a0a39f51eafb" -H "Content-Type:application/json" localhost:8080/flights -X POST -d @flight.json
HTTP/1.1 201 
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Wed, 15 Aug 2018 12:41:09 GMT

{"id":3,"origin":"Beijing","destination":"Washington","airline":"Air China","flightNumber":null,"date":null,"traveler":"Jack Chen"}
[root@contoso ~]#

