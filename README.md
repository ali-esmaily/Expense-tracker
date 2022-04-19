
## Expense tracker

Most people know the importance of keeping business receipts in case they’re audited. The problem with paper receipts is that it’s easy to lose such an important document. By immediately uploading these into an expense tracker app, you save space and time instead of having to dig through a shoebox full of receipts

##Object:
Creating a simple Expense tracker

## features:
 1. OpenAccount
 2. CloseAccount
 3. GetAllAccounts
 4. GetAccountByHolder
 5. DepositTransactions
 6. WithdrawTransactions

## prerequisite

JDK11, Docker and docker-compose

## run

```bash
./mvnw clean package
```

```bash
docker-compose up --build
```

And you can find the Game on http://localhost:8080/

## Documentation 

OpenAPI descriptions are available at the path <b> /v3/api-docs </b>

Access the API documentation at: <b> /swagger-ui.html </b>


