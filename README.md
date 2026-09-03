# E-Commerce Spring API

A Spring Boot REST API for browsing an e-commerce product catalog. The application stores products and reviews in MySQL, supports paginated product listings and filtered search, and seeds a set of demo products on first startup.

## Features

- Paginated product listing
- Product lookup by ID
- Product search by category, price range, keyword, and minimum rating
- Product images and reviews represented in the domain model
- Automatic database schema updates through Hibernate
- Automatic seeding of 15 demo products when the products table is empty

## Tech stack

- Java 21
- Spring Boot 4.0.2
- Spring Web MVC
- Spring Data JPA / Hibernate
- MySQL
- Maven Wrapper

## Project structure

```text
E-Commerce-Spring/
├── src/main/java/com/vgm/ecommercecart/
│   ├── controller/       REST endpoints
│   ├── dto/              Request DTOs
│   ├── entity/           JPA entities
│   ├── repository/       Spring Data repositories
│   ├── seed/             Demo product seeder
│   ├── service/          Application and persistence logic
│   └── spec/             Dynamic search specifications
├── src/main/resources/
│   └── application.properties
├── pom.xml
├── mvnw
└── mvnw.cmd
```

## Prerequisites

- JDK 21 or newer
- MySQL 8 or a compatible MySQL server
- A MySQL database named `vgmcart`

Create the database before starting the application:

```sql
CREATE DATABASE vgmcart;
```

The current default configuration expects:

| Setting | Default |
|---|---|
| Host | `localhost` |
| Port | `3306` |
| Database | `vgmcart` |
| Username | `root` |
| Password | `lmao` |

The host can be overridden with the `MYSQL_HOST` environment variable. The username and password are currently defined directly in `src/main/resources/application.properties`; change them there, or externalize them before deploying to a shared or production environment.

## Run locally

From the repository root:

```bash
cd E-Commerce-Spring
```

On Windows PowerShell:

```powershell
./mvnw.cmd spring-boot:run
```

On macOS/Linux:

```bash
./mvnw spring-boot:run
```

The API is available at `http://localhost:8080` by default. On the first successful startup, the seeder inserts the demo products if the products table is empty.

## Build and test

```bash
./mvnw clean test
```

On Windows PowerShell, use `./mvnw.cmd clean test` instead.

## API reference

### List products

```http
GET /api/products?page=0&size=5
```

Query parameters:

- `page` — zero-based page number; defaults to `0`
- `size` — number of products per page; defaults to `5`

Example response shape:

```json
{
  "products": [],
  "totalProducts": 15
}
```

### Get a product

```http
GET /api/products/{id}
```

Returns a product when the ID exists. A missing ID currently results in a server error containing `Product Not Found with the Id: ...`; a dedicated exception handler is not yet configured.

### Search products

```http
GET /api/products/search
```

Supported optional query parameters:

| Parameter | Meaning |
|---|---|
| `category` | Exact category match, for example `Laptop` |
| `minPrice` | Minimum price, inclusive |
| `maxPrice` | Maximum price, inclusive |
| `keyword` | Matches product name or description |
| `ratings` | Minimum rating, inclusive |

Examples:

```bash
curl "http://localhost:8080/api/products/search?category=Laptop&minPrice=100000"
curl "http://localhost:8080/api/products/search?keyword=wireless&ratings=4.5"
curl "http://localhost:8080/api/products/search?minPrice=5000&maxPrice=50000"
```

## Data model

- `Product` — name, price, description, category, seller, stock, rating, review count, images, and reviews
- `ProductImage` — image URL and provider/public ID
- `ProductReview` — rating, comment, and product association

Products and their child images/reviews use cascading persistence with orphan removal. The current demo seed data does not include images or reviews.

## Configuration notes

The default configuration contains:

```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

`ddl-auto=update` is convenient for local development but should be replaced with a controlled migration strategy for production. SQL logging is enabled, so set `spring.jpa.show-sql=false` when quieter logs are preferred.

## Current limitations

- There is no authentication or authorization layer.
- There are no product create, update, or delete endpoints.
- `ProductReviewController` contains review persistence logic but currently has no `@PostMapping` (or other HTTP method mapping), so no HTTP endpoint is exposed for adding reviews yet.
- Validation annotations exist on entities, but request-body validation and centralized error handling are not currently configured.
- The search keyword predicate lowercases the input but does not explicitly lowercase database fields; case-insensitive behavior therefore depends on the MySQL collation.
- The configured database password is a development default and should not be committed for real deployments.

## License

No license has been specified for this project yet.
