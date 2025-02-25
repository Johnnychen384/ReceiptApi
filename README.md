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
