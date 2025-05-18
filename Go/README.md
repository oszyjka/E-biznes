Run app: **go run .**

Examples endpoints (bash):
Products:
1. Show all: ```curl http://localhost:8080/products```     
2. Show by id: ```curl http://localhost:8080/products/1```      
3. Update: ```curl -X PUT http://localhost:8080/products/1 -H "Content-Type: application/json" -d '{"name": "Gaming Laptop", "price": 2499, "category_id": "1"}'```
4. Delete: ```curl -X DELETE http://localhost:8080/products/1```
5. Add: ```curl -X POST http://localhost:8080/products -H "Content-Type: application/json" -d '{"name": "Laptop", "price": 1999, "category_id": 1}'```

Categories:
1. Show all: ```curl http://localhost:8080/categories```
2. Show by id: ```curl http://localhost:8080/categories/1```  
3. Add: ```curl -X POST http://localhost:8080/categories -H "Content-Type: application/json" -d '{"name": "Electronics"}'```

Cart:
1. Show all: ```curl http://localhost:8080/carts```
2. Show by id: ```curl http://localhost:8080/carts/1```  
3. Add: ```curl -X POST http://localhost:8080/carts -H "Content-Type: application/json" -d '{"user": "Alice", "total": 0}'```

Filters:
1. Min price: ```curl "http://localhost:8080/products?min_price=500"```
2. Max price: ```curl "http://localhost:8080/products?max_price=150"```
3. Category: ```curl "http://localhost:8080/products?category_id=1"```
4. Sort by category & price ascending: ```curl "http://localhost:8080/products?category_id=1&sort=asc"```
5. SOrt by price descending: ```curl "http://localhost:8080/products?sort=desc"```










