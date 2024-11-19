# Building the app
Assuming you hava Java 17 installed

```bash
./gradlew clean build -x test
```

# Running the service

```bash
# Run on default port (8080)
./gradlew :bootRun

# Specify port
./gradlew bootRun --args='--server.port=8081'
```

# Using the APIs

### Process a receipt
```bash
curl -X POST http://127.0.0.1:8080/receipts/process \
-H "Content-Type: application/json" \
-d '{
  "retailer": "Target",
  "purchaseDate": "2022-01-01",
  "purchaseTime": "13:01",
  "items": [
    {
      "shortDescription": "Mountain Dew 12PK",
      "price": "6.49"
    },
    {
      "shortDescription": "Emils Cheese Pizza",
      "price": "12.25"
    },
    {
      "shortDescription": "Knorr Creamy Chicken",
      "price": "1.26"
    },
    {
      "shortDescription": "Doritos Nacho Cheese",
      "price": "3.35"
    },
    {
      "shortDescription": "   Klarbrunn 12-PK 12 FL OZ  ",
      "price": "12.00"
    }
  ],
  "total": "35.35"
}'
```
### Response

```bash
{"id":"bfad6dc3-8a9f-4df3-9a51-f1b101d8f261"}
```

### Calculate "points"

```bash
curl http://127.0.0.1:8080/receipts/bfad6dc3-8a9f-4df3-9a51-f1b101d8f261/points
```

### Response

```bash
{"points":28}
```