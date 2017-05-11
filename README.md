# Coding Challenge

### Summary
I have implemented this task using grails 2.5.0, as installed by the grailsw.bat file included in the template.

It is implemented as a standard grails app with rendered views, though I have more recent experience using AngularJs/ReactJS combined with a RESTful API to do similar interfaces. I have never used grails before this however, so I used it as an opportunity to learn the framework.

I have tried to put the actual data loading and service logic into services, so that the current services could be migrated to a RESTful controller in future. With more time I would have tried this with Grails' support for REST.

### Compile and run
1. Run the grailsw.bat file provided with the template.
2. In the grails command line, ```run-app```
3. The application will be hosted at http://localhost:8080/seCodingChallenge
4. The greenmail test server will be hosted at http://localhost:8080/seCodingChallenge/greenmail/list
5. The home page is the account summary screen, with a menu bar allowing navigation to the "Pay" screen.
6. Run tests using ```test-app```.
7. I only tested in Google Chrome.

### Data
Three test accounts are created and initialized on application startup.

### Modelling Notes
- I modelled transactions using a simple double entry accounting model.
- I denormalized the account balances to allow for fast lookup of the account balance without having to sum over all transactions.
  - This has the overhead of needing to maintain the integrity of this value, however as the transaction table grows over time, summing all transactions each time you need the balance would not scale well.
  - This design would depend on how well you could control the way that transactions are populated, as the account balance would need to change with every transaction change.
  - If transactions were mutable however, I would use a different design that optimized the aggregation of transactions somehow.
  
### Testing
I'd not used grails before this task (or java for 9 years!), so I focused on unit tests of the service, controller and model validation logic to keep things simple.

With more time, I would have created an end-to-end funcitonal test suite and integration tests to ensure the database integrity and other concurrency/transactional scenarios.
