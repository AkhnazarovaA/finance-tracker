# Finance tracker  
A Spring boot REST API for tracking income and expenses.
## Tech Stack 
- Java
- Spring Boot
- Maven
- PostgreSQL
- Docker
- Spring Data JPA
- Jakarta Validation

## Features
- Create transactionEntity
- Get all transactions
- Get transactionEntity by ID
- Update transactionEntity
- Partially update transactionEntity
- Delete transactionEntity
- Validate request data

## Project structure
- `controller` - handles HTTP requests
- `service` - contains business logic 
- `repository` - communicates with the database
- `model` - contains JPA entities and enums
- `dto` - contains request and response data objects

## API endpoints
| Method | Endpoint                | Description                     |
|---|-------------------------|---------------------------------|
| GET | `/api/transactions`     | Get all transactions            |
| GET | `/api/transactions/{id}` | Get transactionEntity by ID           |
| POST | `/api/transactions`     | Create a transactionEntity            |
| PUT | `/api/transactions/{id}` | Update a transactionEntity            |
| PATCH | `/api/transactions/{id}` | Partially update a transactionEntity  |
| DELETE | `/api/transactions/{id}` | Delete a transactionEntity |

## Example request
```json
{
   "transactionType": "INCOME",
   "amount": 500000.00,
   "category": "SALARY",
   "description": "Monthly income",
   "transactionDate": "2026-04-21"
}
