# CustomerAccountService

url : localhost:8082/authenticate
requestBody:
{
			"userName": "test",
			"password":"password"
}
response:<jwtToken>

GET(All User)
url : localhost:8082/user
response:
{
    "id": 2,
    "userName": "nameupdated",
    "dateOfBirth": "2019-01-02T06:29:59.862+0000",
    "role": {
        "id": 1,
        "roleName": "Customer",
        "roleCode": "001"
    },
    "gender": "M",
    "phoneNumber": "9887887",
    "password": "updated"
}

GET(Specific User)
url : localhost:8082/user/2
response:
{
    "id": 2,
    "userName": "nameupdated",
    "dateOfBirth": "2019-01-02T06:29:59.862+0000",
    "role": {
        "id": 1,
        "roleName": "Customer",
        "roleCode": "001"
    },
    "gender": "M",
    "phoneNumber": "9887887",
    "password": "updated"
}

POST
URL:localhost:8082/useradd
Header :
Authorization:<jwtToken>
RequestBody:{
			"userName": "name123",
			"dateOfBirth": "2019-01-02T06:29:59.862+00:00",
			"gender": "M",
			"phoneNumber": "111",
			"password" : "tttt1",
            "role":{
                "roleName":"Customer",
                "roleCode":"001"
            }
		}
Response:

{
    "id": 4,
    "userName": "name123",
    "dateOfBirth": "2019-01-02T06:29:59.862+0000",
    "role": {
        "id": 1,
        "roleName": "Customer",
        "roleCode": "001"
    },
    "gender": "M",
    "phoneNumber": "111",
    "password": "tttt1"
}

PUT
URL:localhost:8082/user/2
Header :
Authorization:<jwtToken>
RequestBody:{
			"userName": "name123",
			"dateOfBirth": "2019-01-02T06:29:59.862+00:00",
			"gender": "M",
			"phoneNumber": "111",
			"password" : "tttt1",
            "role":{
                "roleName":"Customer",
                "roleCode":"001"
            }
		}
Response:

{
    "id": 4,
    "userName": "name123",
    "dateOfBirth": "2019-01-02T06:29:59.862+0000",
    "role": {
        "id": 1,
        "roleName": "Customer",
        "roleCode": "001"
    },
    "gender": "M",
    "phoneNumber": "111",
    "password": "tttt1"
}

DELETE
URL:localhost:8082/user/2
Header :
Authorization:<jwtToken>
