Design and implement a RESTful API (including data model and the backing implementation)
for money transfers between accounts.
Explicit requirements:
1. You can use Java, Scala or Kotlin.
2. Keep it simple and to the point (e.g. no need to implement any authentication).
3. Assume the API is invoked by multiple systems and services on behalf of end users.
4. You can use frameworks/libraries if you like (except Spring), but don't forget about
requirement #2 – keep it simple and avoid heavy frameworks.
5. The datastore should run in-memory for the sake of this test.
6. The final result should be executable as a standalone program (should not require
a pre-installed container/server).
7. Demonstrate with tests that the API works as expected.

API:

http://localhost:8080/account/create - creates new account
    JSON request body example: {"name": "visa", "balance": 23223}
http://localhost:8080/account/transfer - transfer money from one account to another.
    JSON request body example: {"fromAccount": "0", "toAccount": "1", "amount": 2000}
http://localhost:8080/account/{id} - gets account with specifed {id}
