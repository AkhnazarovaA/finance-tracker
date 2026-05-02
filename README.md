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
- Create transaction
- Get all transactions
- Get transaction by ID
- Update transaction
- Partially update transaction
- Delete transaction
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
| GET | `/api/transactions/{id}` | Get transaction by ID           |
| POST | `/api/transactions`     | Create a transaction            |
| PUT | `/api/transactions/{id}` | Update a transaction            |
| PATCH | `/api/transactions/{id}` | Partially update a transaction  |
| DELETE | `/api/transactions/{id}` | Delete a transaction |

## Example request
```json
{
   "transactionType": "INCOME",
   "amount": 500000.00,
   "category": "SALARY",
   "description": "Monthly income",
   "transactionDate": "2026-04-21"
}
