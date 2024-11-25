# Run app with docker
In the project's root directory, run:
```bash
docker compose up -d --build
```
This will expose the APIs on port 8080. The app will run with multiple receipt-processor-service instances hidden
behind a round-robin loadbalancer. Receipts are currently stored in a hazelcast map accessible from each instance.

Note: You might have to wait a minute for all the services to register with Eureka. If you visit http://localhost:8761,
you should see a loadbalancer and at least one receipt processor instance.

To stop the app, run:
```bash
docker compose down
```

# Run app from command line

### Building the app
Assuming you hava Java 17 installed

```bash
./gradlew clean build
```

### Running the service
Run the service on port 8081 simple by executing.
```bash
./gradlew :receipt-processor-service:bootRun
```

Or you can run multiple instances behind a loadbalancer. The API will then be listening on port 8080:
```bash
# Open a new terminal window for each of the following:
./gradlew :eureka-server:bootRun
./gradlew :loadbalancer:bootRun
./gradlew :receipt-processor-service:bootRun --args='--server.port=8081'
./gradlew :receipt-processor-service:bootRun --args='--server.port=8082'
./gradlew :receipt-processor-service:bootRun --args='--server.port=8083'
# ...
```
Note: You might have to wait for service registration to complete before calling the APIs.
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
#### Response

```bash
{"id":"bfad6dc3-8a9f-4df3-9a51-f1b101d8f261"}
```

### Calculate "points"

```bash
curl http://127.0.0.1:8080/receipts/bfad6dc3-8a9f-4df3-9a51-f1b101d8f261/points
```

#### Response

```bash
{"points":28}
```