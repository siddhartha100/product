Product application is an application that can do the following operations
1. Fetch product information and display that to the client
2. Update product information to a nosql database.

Technologies used:
1. Java 11
2. Spring Boot 2
3. Spring Boot Data Mongo DB
4. Spring Boot Web
5. Spring Boot Test
6. Lombok

BaseURL: http://127.0.0.1:32000/v1

Endpoints:

1. Find product information based in product id
- Url - {baseUrl}/product/{product-id}
- Http Method - GET
- Response -
   {
   "name": "The Big Lebowski (Blu-ray)",
   "id": 13860428,
   "current_price": {
   "value": 13.34,
   "currency_code": "USD"
   }
   }
- Successful Http response code - 200

2. Update product information to database
- Url - {baseUrl}/products/{product-id}
- Http Method - PUT
- Request  - {
  "name": "The Big Lebowski",
  "id": 13860433,
  "current_price": {
  "value": 12.00,
  "currency_code": "INR"
  }
  }
- Response - {
  "name": "The Big Lebowski",
  "id": 13860433,
  "current_price": {
  "value": 12.00,
  "currency_code": "INR"
  }
  }
- Successful Http response code - 202



