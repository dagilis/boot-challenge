# boot-challenge

## Task
We would like you to model an account service according to REST guidelines. It
should be able to create, access, find accounts and to be able to transfer money between
them.

## How to launch

Launching web-service on http://localhost:8080.

`$ mvn spring-boot:run`

This setup will contain couple example accounts stored in the database. Most simple approach is simply to open
following url which will provide JSON formatted entries: http://localhost:8080/accounts.

### Listing all accounts
Simplest request listing all items: 
`curl  http://localhost:8080/accounts`

### Searching accounts by name
Listing multiple accounts with similar name using `?name=<search>` query: 
`curl  http://localhost:8080/accounts?name=Ma`

### Creating new Account
Creating new Account requires to post data to the same endpoint:
`curl --header "Content-Type: application/json" --request POST  --data '{"name":"example","currency":"EUR","balance":10.1,"treasury":false}'  http://localhost:8080/accounts`

Main fields in the entity (as per specification):
- name - Account holder name
- currency - 3 letter (in capital letters) ISO standard currency name
- balance - current account balance
- treasury - identifier if it is a treasury account 

### Transfer the money
Money transfer is implemented on transactions endpoint. This if the following example request:
`curl --header "Content-Type: application/json" --request POST  --data '{"fromName":"Vytautas", "toName":"Marie","currency":"EUR","amount":1.0}'  http://localhost:8080/transactions`

Main fields in the entity:
- fromName - account holder name who is making the transfer
- toName - account holder who is receiveing the funds
- currency -  transaction currency (ISO Standard 3 letter name of the currency, e.g. EUR)
- amount - amount to be transfered (in the crrency specified above)

### Running Unit Tests:
`$ mvn test`

## Future Improvements
- Currently, search is implemented only as a simple 'Like' SQL operator on name column. Could be improved by 
including other columns or even elastic search.
- Transactions implemented only as a rest request which directly updates the entities. More appropriate would be
split this request into two separate events/transactions: one debit and one credit to match the duality principle from 
accounting as well as more Event Driven architecture approach.
- Adding update to the Account section with PUT method (validation for the treasury accounts to be immutable etc.)
- Integration test in separate IT source-root to showcase bigger scenarios. On top - add Spring Docs as explanation how to use API.
- Custom exception handler to provide more clear error messages (provide context form the validators)
- Add pagination support to limit too long responses form accounts
- Current transaction validation is done using Bean Validation. This might be inefficient for fields which need to 
use DB access - more sophisticated approach could be used.
- Wanted to add Angular JS Front End for listing and adding accounts and transactions - But that would have extended the time beyond expectations