Endpoints

Users
GET /users/{id}Retrieves a user by ID.
Response: 200 OK with JSON { id, name, email } if found; 404 if not found.

POST /usersCreates a new user.
Request Body: JSON { name, email }
Response: 201 Created with JSON { id, name, email }.

Products
GET /products/{id}Retrieves a product by ID.
Response: 200 OK with JSON { id, name, price } if found; 404 if not found.

Tested Functionality

UserServiceTest
testGetUserById_ReturnsUser_WhenExistsVerifies that an existing user is returned correctly.
testGetUserById_Throws_WhenNotFoundVerifies that requesting a non-existent user results in a UserNotFoundException.
testCreateUser_SuccessVerifies that creating a new user assigns an ID and retains provided fields.

ProductServiceTest
testGetProductById_FoundVerifies that an existing product is returned correctly.
testGetProductById_NotFoundVerifies that requesting a non-existent product results in a ProductNotFoundException.

Edge Cases

Users
Null ID on getUserById(null) → throws NullPointerException.
Negative ID (e.g. -1) → throws IllegalArgumentException.
Zero ID → throws IllegalArgumentException.
Non-existent ID (valid positive but not found) → throws UserNotFoundException.
Null User on createUser(null) → throws NullPointerException.
Missing name (name == null or blank) → throws IllegalArgumentException.
Missing email (email == null or blank) → throws IllegalArgumentException.
Invalid email format → throws IllegalArgumentException.
Duplicate email → repository throws, service propagates IllegalStateException.

Products
Null ID on getProductById(null) → throws IllegalArgumentException.
Negative ID (e.g. -5) → throws IllegalArgumentException.
Zero ID → throws IllegalArgumentException.
Non-existent ID (valid positive but not found) → throws ProductNotFoundException.


Suggested AI Prompts (“Questions”)
Use these to drive each TDD step with the AI. Tweak the package names or method signatures as needed.
1.	Project & Test Setup
•	“Create a Maven pom.xml for a Spring Boot (Java 17) project that uses TestNG and Mockito.”
•	“Generate a TestNG suite XML (testng.xml) that picks up all tests under com.example.demo.”
2.	Happy-Path Service Tests
•	“Write a TestNG + Mockito unit test for UserService.getUserById(Long id) that verifies the happy path (existing user).”
•	“Write a TestNG + Mockito unit test for ProductService.getProductById(Long id) happy path.”
3.	Implement Production Code
•	“Based on the UserServiceTest, implement UserService, its repository interface, and an in-memory repository.”
•	“Implement ProductService, repository, and in-memory repository to satisfy the happy-path tests.”
4.	Error-Path Service Tests
•	“Write a TestNG + Mockito test for UserService.getUserById when the user is not found (should throw UserNotFoundException).”
•	“Write a TestNG + Mockito test for ProductService.getProductById when not found (throws ProductNotFoundException).”
5.	Implement Error-Path Logic
•	“Update UserService.getUserById to throw UserNotFoundException for missing users, and ProductService similarly.”
6.	Edge-Case Service Tests
•	“Write TestNG tests for UserService edge cases: null ID, negative ID, zero ID, null user input, missing name/email, invalid email, duplicate email.”
•	“Write TestNG tests for ProductService edge cases: null/negative/zero ID, non-existent ID.”
7.	Implement Edge-Case Handling
•	“Add null checks and validation in UserService and ProductService so those edge-case tests pass.”
8.	Controller‐Layer Tests (Optional)
•	“Write @WebMvcTest (MockMvc) tests for UserController verifying HTTP 200 for good requests and 4xx for bad requests.”
•	“Do the same for ProductController.”
9.	Integration / E2E Tests (Optional)
•	“Create Spring Boot integration tests (using @SpringBootTest and TestRestTemplate) to exercise the live HTTP endpoints.”
10.	Documentation & README
•	“Generate or update a README.md describing endpoints, tested functionality, and edge cases.”

⸻

Presentation Flow
Organize your talk/demo into clear phases. Plan for ~20–25 minutes total:
1.	Intro (2 min)
•	Goals: show TDD in action and how AI accelerates writing tests.
•	Quick project overview (Users & Products API).
2.	Project Setup (3 min)
•	Show initial empty Spring Boot project.
•	Ask AI to generate pom.xml and testng.xml.
•	Run “mvn test” → no tests, build success.
3.	Phase 1: Happy-Path Service Tests (4 min)
•	Ask AI for UserServiceTest happy path.
•	Paste test, run → fails.
•	Ask AI to implement service+repo.
•	Run tests → green.
4.	Phase 2: Error-Path Tests (4 min)
•	AI writes tests for missing-user and missing-product.
•	Run → fails.
•	AI implements exceptions in services.
•	Run → green.
5.	Phase 3: Edge-Case Tests (5 min)
•	Ask AI to list edge cases; then to write tests.
•	Demonstrate a failing edge-case test (e.g. null ID).
•	AI adds validation code.
•	Run → all green.
6.	(Optional) Phase 4: Controller Tests (3 min)
•	AI writes MockMvc tests.
•	Show green results.
7.	Wrap-Up & Q&A (3 min)
•	Highlight how AI reduced boilerplate.
•	Discuss next steps (integration tests, CI integration).
•	Take questions.
