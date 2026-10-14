# NutriCare AI — Spring Boot Backend

Production-ready Spring Boot backend for **NutriCare AI — Intelligent Personalized Wellness & Nutrition Management Platform**.

## Architecture & Tech Stack

- **Java Version:** 21 (LTS)
- **Framework:** Spring Boot 3.2.5
- **Persistence:** Spring Data JPA / Hibernate
- **Database:** MySQL 8
- **Security:** Spring Security 6 with BCrypt password hashing & stateless JJWT tokens
- **Build Tool:** Apache Maven (with included `mvnw` wrapper)

## Project Structure

```
backend-springboot/
├── pom.xml
├── mvnw / mvnw.cmd
├── Dockerfile
├── .env.example
├── src/
│   ├── main/
│   │   ├── java/com/nutricare/ai/
│   │   │   ├── NutriCareApplication.java
│   │   │   ├── config/
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   ├── JwtTokenProvider.java
│   │   │   │   └── JwtAuthenticationFilter.java
│   │   │   ├── entity/
│   │   │   │   ├── User.java
│   │   │   │   ├── WellnessProfile.java
│   │   │   │   ├── WaterLog.java
│   │   │   │   ├── MealSelection.java
│   │   │   │   ├── MealPlan.java
│   │   │   │   ├── UserReminder.java
│   │   │   │   ├── ActivityLog.java
│   │   │   │   └── ShoppingItem.java
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── WellnessProfileRepository.java
│   │   │   │   ├── WaterLogRepository.java
│   │   │   │   ├── MealSelectionRepository.java
│   │   │   │   ├── MealPlanRepository.java
│   │   │   │   ├── UserReminderRepository.java
│   │   │   │   ├── ActivityLogRepository.java
│   │   │   │   └── ShoppingItemRepository.java
│   │   │   ├── service/
│   │   │   │   ├── AuthService.java
│   │   │   │   ├── WellnessService.java
│   │   │   │   └── MealPlanService.java
│   │   │   ├── controller/
│   │   │   │   ├── HealthController.java
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── WellnessController.java
│   │   │   │   └── MealPlanController.java
│   │   │   └── dto/
│   │   │       ├── AuthRequest.java
│   │   │       ├── RegisterRequest.java
│   │   │       ├── AuthResponse.java
│   │   │       ├── ProfileDto.java
│   │   │       ├── WaterDto.java
│   │   │       ├── MealSelectionDto.java
│   │   │       ├── MealPlanDto.java
│   │   │       ├── ReminderDto.java
│   │   │       ├── ActivityDto.java
│   │   │       ├── ShoppingDto.java
│   │   │       └── ApiResponse.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/nutricare/ai/
│           └── NutriCareApplicationTests.java
```

## Running Locally

1. Ensure MySQL is running and the database `nutricare_ai` exists:
   ```bash
   mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS nutricare_ai;"
   ```

2. Export environment variables (optional if using defaults in `application.properties`):
   ```bash
   export SPRING_DATASOURCE_URL="jdbc:mysql://localhost:3306/nutricare_ai?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
   export SPRING_DATASOURCE_USERNAME="root"
   export SPRING_DATASOURCE_PASSWORD="your_password"
   export PORT=8080
   ```

3. Build and run using the Maven wrapper:
   ```bash
   ./mvnw clean spring-boot:run
   ```

   The backend will be available at `http://localhost:8080`.

## API Endpoints

### Health Check
- `GET /api/health` — Check server health

### Authentication
- `POST /api/auth/register` — Register new user (`name`, `email`, `password`)
- `POST /api/auth/login` — Login (`email`, `password`), returns JWT token & user info
- `GET /api/auth/me/{userId}` — Get user profile info

### Wellness Profile
- `GET /api/wellness/profile/{userId}` — Retrieve saved profile
- `POST /api/wellness/profile` — Save/update profile

### Water Tracker
- `GET /api/wellness/water/{userId}` — Get logged water for date
- `POST /api/wellness/water` — Log water intake

### Meal Planning (7-Day MySQL Persistence)
- `GET /api/wellness/plans/{userId}` — Get weekly meal plans
- `POST /api/wellness/plans` — Save single meal plan for date & category
- `POST /api/wellness/plans/batch?userId={id}` — Batch sync complete week
- `DELETE /api/wellness/plans/{userId}?date={date}&category={category}` — Clear meal selection
- `POST /api/wellness/meals` — Legacy compatibility route for single meal selection

### Meal Reminders (4 Independent Reminders)
- `GET /api/wellness/reminders/{userId}` — Retrieve 4 meal reminder times & status
- `POST /api/wellness/reminders` — Save 4 meal reminder times & enabled state

### Activity Planner
- `GET /api/wellness/activity/{userId}` — Retrieve steps, exercise minutes, calories, and routine checks
- `POST /api/wellness/activity` — Save daily activity

### Shopping List
- `GET /api/wellness/shopping/{userId}` — Retrieve saved shopping items & checked states
- `POST /api/wellness/shopping` — Save shopping items & checked states

## Render Deployment

1. On [Render](https://render.com), create a new **Web Service**.
2. Connect your GitHub repository and specify the **Root Directory** as `backend-springboot`.
3. Choose **Docker** as the environment (the included `Dockerfile` will build and package the app).
4. In **Environment Variables**, set:
   - `SPRING_DATASOURCE_URL`: JDBC URL to your hosted MySQL database (e.g. from Aiven, PlanetScale, Render, or Railway)
   - `SPRING_DATASOURCE_USERNAME`: your hosted MySQL username
   - `SPRING_DATASOURCE_PASSWORD`: your hosted MySQL password
   - `JWT_SECRET`: a secure 32+ character string
   - `CORS_ALLOWED_ORIGINS`: your deployed Vercel frontend URL (e.g. `https://nutricare-ai.vercel.app`)
   - `PORT`: `8080` (Render binds to this port automatically)
