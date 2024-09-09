# DAT250: Software Technology Experiment Assignment 2

Simple REST API for Poll app sing Spring Boot as specified by
[DAT250 Assignment 2](https://github.com/selabhvl/dat250public/blob/master/expassignments/expass2.md)

## Project implementation

- <b>Model:</b> User, Poll, Vote, VoteOption
- <b>Repository:</b> Transactions with database, using implementing CrudRepository and using H2
- <b>Service:</b> Defining how the model can be read and modified. 
- <b>Controller:</b> Web API, handling HTTP requests and responses. 

Data Transfer Objects have been used to handle serialization such that circular references are avoided. 

### Test Scenarios

[HTTP request tests](src/test/TestScenario1.http)

Testing creation of users, polls, voting and modifying existing votes, and deleting polls. 
The tests verify receiving the correct status code signifying that the sequence of events run without issues. 

### Pendign issues
- More exhaustive tests are needed to verify that all create, read, update and delete actions are handled correctly.
- Delete action is not working in the test scenario as cascades are not configured fully.
- Automatic testing methods has not been implemented. 
- Data for related entities when creating users or votes is not being stored since merging with existing data has not been fully implemented.






