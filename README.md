# Spring Boot API

## Running with Docker

This application is containerized with Docker for easy deployment and testing.

### Prerequisites
- Docker and Docker Compose installed on your machine

### Setup Instructions

1. Clone this repository:
   ```
   git clone git@github.com:Johnnychen384/ReceiptApi.git
   cd your-repo-name
   ```

2. Create an environment file:
   ```
   cp .env.example .env
   ```

3. Edit the `.env` file with your own secure values:
   ```
   JWT_SECRET=your_secure_jwt_secret_key_here
   JWT_EXPIRATION=900000
   ```

4. Build and run the application:
   ```
   docker-compose up
   ```

5. The API will be available at http://localhost:8080

### Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| JWT_SECRET | Secret key for JWT token signing | None (required) |
| JWT_EXPIRATION | Token expiration time in milliseconds | 900000 |
| SPRING_PROFILES_ACTIVE | Spring profile to activate | dev |


## Authentication Instructions

This API uses JWT token authentication. Before accessing any endpoints, you'll need to obtain an authentication token.

### Getting an Authentication Token

1. Make a POST request to the token endpoint:
   ```
   POST localhost:8080/token
   ```

2. No payload or body is required for this request.

3. The response will contain your JWT token:
   ```
   eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
   ```

### Using the Token

For all subsequent API requests, include the token in the Authorization header:

```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

#### Example using Postman:
1. Make a GET request to `http://localhost:8080/token`
2. Copy the token value from the response
3. For other requests, go to the "Authorization" tab
4. Select "Bearer Token" from the dropdown
5. Paste your token in the "Token" field

Remember that JWT tokens will expire after a certain period. You'll need to obtain a new token if you receive authentication errors.