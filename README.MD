<h1>ReadingIsGood</h1>
ReadingIsGood is an online books retail firm which operates only on the Internet. Main
target of ReadingIsGood is to deliver books from its one centralized warehouse to their
customers within the same day. That is why stock consistency is the first priority for their
vision operations.

---------------

<h1>Using Tech Stack</h1>

* <h5>Java 11<h5>
* <h5>Maven<h5>
* <h5>Spring Boot<h5>
* <h5>H2 Database<h5>
* <h5>Docker<h5>
* <h5>Lombok<h5>
* <h5>Swagger UI<h5>
* <h5>JWT Web Token<h5>
* <h5>Spring Security<h5>
* <h5>JUnit<h5>
* <h5>Spring JPA<h5>
* <h5>SLF4J Logger<h5>
------------------

ReadingIsGood have 5 different controllers:
* Customer Controller
    * persist new customers
    * query all orders of the customer by pagination
* Book Controller
    * persist new book
    * update book's stock
* Order Controller
    * persist new order
    * query order details by order id
    * update book stock
    * query all orders between two dates
* Statistics Controller
    * get customers total order, total purchase and total book by monthly
* Authorization Controller (Optional)
    * authority registration
    * authority login

<h1>Customer Controller</h1>

* <h3> @PostMapping("/createCustomer") </h3>
* <h3> @GetMapping("/orderDetails/{id}") </h3>

<h1>Book Controller</h1>

* <h3>@PostMapping("/addBook")</h3>
* <h3>@PostMapping("/updateBookStock")</h3>

<h1>Order Controller</h1>

* <h3>@PostMapping("/addOrder")</h3>
* <h3>@GetMapping("/orderDetails/{id}")</h3>
* <h3>@PostMapping("/updateStock")</h3>
* <h3>@GetMapping("/getOrders/{startDate}/{endDate}")</h3>

<h1>Statistics Controller</h1>


* <h3>@GetMapping("/allStatistics/{id}")</h3>

<h1>Authorization Controller</h1>

* <h3>@PostMapping("/login")</h3>
* <h3>@PostMapping("/register)</h3>


You can also see the swagger documentation at http://localhost:8080/swagger-ui.html

Postman collections are located under the postmanCollections folder

----------------

<h1>How to Set Up</h1>

ReadingIsGood application can run with docker image using below script:

First go to the home directory of the project (where the src is)

Make sure you get the jar pack. (Maven clean, Maven install). Then you can see jar under target folder.

* docker build -t readingisgood
* docker run -p 8080:8080 readingisgood 

Before making any query, you need to register to the system and then login. You can make any request in the system by using the token you received as a header as a result of login. Otherwise, this will not be possible as you will be unauthorized.

You can see examples in your Postman Collections.