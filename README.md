# ZSS-Interview

#### To view the endpoints
The API has been documented with [Swagger](https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api) to see all the endpoints go to the browser and run the http://localhost:8080/zss/swagger-ui/

### To add book 
Go to postman on url : http://localhost:8080/zss/api/v1.0/books and send a post request in the following format. `Note` : for updating the format is the same but you send a put request
```postman

{
    "id":"",
    "status":"ACTIVE",
    "dateCreated":"",
    "title":"book 9",
    "description":"This is an action movie",
    "price":"100.34",
   "categoryDto": {
        "id": "",
        "status": "ACTIVE",
        "dateCreated": "",
        "title": "Horror Novels"
    }

}

```

### To add a category
Send a post request on url:http://localhost:8080/zss/api/v1.0/books with the format below
```postman
"categoryDto": {
        "id": "",
        "status": "ACTIVE",
        "dateCreated": "",
        "title": "Horror Novels"
    }

```

### To search for a book in a certain Category
Send a get request to the url :  http://localhost:8080/zss/api/v1.0/books/{categoryname}


### Purchasing a book 
To purchase a book send a post request on url : http://localhost:8080/zss/api/v1.0/books/purchase/{bookId}
```postman
The body can be empty
```

### Other Endpoints
For more information regarding the endpoints use swagger documentation on url : http://localhost:8080/zss/swagger-ui/
