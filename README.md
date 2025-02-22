# Currency Converter API  

## 📌 Overview  
This is a Spring Boot application that fetches real-time exchange rates and converts currencies using an external API.

## 🚀 Technologies Used  
- Java 17  
- Spring Boot  
- Maven  
- RestTemplate  

## 🔥 API Endpoints  

### 1️⃣ Get Exchange Rates  
**GET /api/rates?base=USD**  
Fetches exchange rates for the given base currency (default: USD).  

Response Example:
```json
{
    "base": "USD",
    "rates": {
        "EUR": 0.92,
        "INR": 82.50
    }
}
```
  
### 2️⃣ Convert Currency  
**POST /api/convert**  
Converts an amount from one currency to another.  

Request Body Example:
```json
{
    "from": "USD",
    "to": "EUR",
    "amount": 100
}
```

Response Example:
```json
{
    "from": "USD",
    "to": "EUR",
    "amount": 100,
    "convertedAmount": 92.0
}
```

## ❌ Error Handling  
- **Invalid currency** → Returns `"Invalid base currency"` or `"Invalid target currency"`.  
- **API failure** → Returns `"Exchange rate service unavailable"`.

## 🛠 How to Run Locally  

### 1️⃣ Install Prerequisites  
- Install [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)  
- Install [Maven](https://maven.apache.org/download.cgi)  

### 2️⃣ Clone the Repository  
```sh
git clone <your-git-repo-url>
cd currency-converter
```

### 3️⃣ Build and Run  
```sh
mvn clean install  
mvn spring-boot:run  
```

### 4️⃣ Access the API  
- **Base URL**: `http://localhost:8080/api`  

## 📄 API Documentation  
📌 **Postman Collection**: [Download Here](https://documenter.getpostman.com/view/40940048/2sAYdcrC1M)

