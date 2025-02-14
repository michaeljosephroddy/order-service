## **Order Service API**

### **Description**

The Order Service API is a RESTful service developed with a microservices architecture. It showcases proficiency in modern API development, demonstrating key features such as:

- One-to-Many Relationships
- Use of Date Objects
- Data Transfer Objects (DTOs)
- Robust Error Handling
- HATEOAS Compliance
- Pagination

---

### **Features**

1. **One-to-Many Relationship**

- The API showcases a one-to-many relationship between Customers and Orders.
- A Customer can have multiple Orders, demonstrating a real-world business model.
- Supported operations:
  - Retrieve all orders for a specific customer.
  - Create, update, and delete orders linked to a customer.
  - Cascading delete: Deleting a customer also deletes their associated orders.

2. **Using Date Objects**

- The API handles date inputs and outputs, using Date objects to:
  - Accept and validate date inputs (e.g., order placement date).
  - Format dates consistently using ISO 8601 (YYYY-MM-DD).
  - Filter and sort data based on dates, such as retrieving orders within a date range.

3. **Data Transfer Objects (DTOs)**

- DTOs are used to structure data between the client and server, ensuring:
  - Decoupling of the internal data model from the API response.
  - Clean, minimal responses exposing only essential fields.
  - Example: `CustomerDTO` includes only name, email, and total orders.

4. **Error Handling**

- Robust error handling mechanisms provide meaningful feedback to API consumers, including:
  - Input validation with appropriate HTTP status codes (e.g., 400 Bad Request, 404 Not Found).
  - Structured error responses for unhandled exceptions.
  - Example: If a user requests a non-existent order, the API returns a 404 error with "Order not found."

5. **HATEOAS (Hypermedia as the Engine of Application State)**

- Enhances discoverability by including hypermedia links in API responses, allowing clients to navigate the API easily.
  - Example: When retrieving a customer, links are provided for fetching their orders, updating details, or deleting the customer.

6. **Pagination**

- Manages large datasets efficiently by returning data in chunks, reducing server load and enhancing UI performance.
- Example: Retrieving a subset of orders with configurable page sizes (e.g., 10 orders per request).

---

### **Endpoints Overview**

- **Customer Endpoints:**

  - `POST /api/customers` – Create a new customer
  - `GET /api/customers` – Retrieve all customers
  - `GET /api/customers/{id}/orders` – Retrieve all orders for a specific customer
  - `DELETE /api/customers/{id}` – Delete a customer and associated orders

- **Order Endpoints:**
  - `POST /api/orders` – Create a new order
  - `GET /api/orders/{id}` – Retrieve a specific order
  - `PUT /api/orders/{id}` – Update an existing order
  - `DELETE /api/orders/{id}` – Delete an order

---

### **Technologies Used**

- **Spring Boot** – For building the RESTful API.
- **Spring Data JPA** – For database interactions.
- **MySQL** – For database for development and testing.
- **DTO Pattern** – For decoupling internal models from API responses.
- **HATEOAS** – For hypermedia-driven API navigation.

---

### **How to Run the Project**

1. **Clone the repository:**

```bash
git clone https://github.com/michaeljosephroddy/order-service.git
```

2. **Navigate to the project directory:**

```bash
cd order-service
```

3. **Build the project using Maven:**

```bash
mvn clean install
```

4. **Run the application:**

```bash
mvn spring-boot:run
```

The API will be accessible at `http://localhost:8080/api`.

---

### **Usage Examples**

- **Create a Customer:**

```bash
curl -X POST -H "Content-Type: application/json" -d '{"name": "John Doe", "email": "john@example.com"}' http://localhost:8080/api/customers
```

- **Retrieve All Orders for a Customer:**

```bash
curl -X GET http://localhost:8080/api/customers/1/orders
```

---

### **Error Handling**

The API provides structured error responses, such as:

- `404 Not Found`: When a requested resource is not found.
- `400 Bad Request`: When input validation fails.
- `500 Internal Server Error`: For unhandled exceptions.

---

### **Project Structure**

```
order-service
│   pom.xml
│   README.md
└───src
    └───main
        └───java
            └───com.example.order_service
                │   Application.java
                ├───controller
                │       OrderController.java
                ├───model
                │       Customer.java
                │       Order.java
                ├───repository
                │       CustomerRepository.java
                │       OrderRepository.java
                └───service
                        OrderService.java
                        OrderServiceImpl.java
```

### **Future Improvements**

- Implementing authentication and authorization.
- Expanding pagination options for more flexible data retrieval.
- Adding unit and integration tests for enhanced test coverage.

---
