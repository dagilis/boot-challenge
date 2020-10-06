# boot-challenge

## Task
We would like you to model an account service according to REST guidelines. It
should be able to create, access, find accounts and to be able to transfer money between
them.

## Future Improvements
- Currently, search is implemented only as a simple 'Like' SQL operator on name column. Could be improved by 
including other columns or even elastic search.
- Transactions are implemented only as a rest request which directly updates the entities. More appropriate would be
split this request into two separate events/transactions: one debit and one credit to match the duality principle from 
accounting as well as more Event Driven architecture approach
- Adding update to the Account section with PUT method (validation for the treasury accounts to be immutable etc.)
