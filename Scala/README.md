Run app: sbt run

Examples endpoints (bash):
Products:
1. Show all: curl http://localhost:9000/products     
2. Show by id: curl http://localhost:9000/products/1      
3. Update: curl -X PUT -H "Content-Type: application/json" -d '{"id":1,"name":"Dress","category":"Clothes","price":200}' http://localhost:9000/products/1
4. Delete: curl -X DELETE http://localhost:9000/products/1
5. Add: curl -X POST -H "Content-Type: application/json" -d '{"id":4,"name":"Chair","category":"Furniture","price":150}' http://localhost:9000/products

Categories:
1. Show all: curl http://localhost:9000/categories  
2. Show by id: curl http://localhost:9000/categories/1   
3. Update: curl -X PUT -H "Content-Type: application/json" -d '{"id":2,"name":"Office Furniture"}' http://localhost:9000/categories/2
4. Delete: curl -X DELETE http://localhost:9000/categories/2
5. Add: curl -X POST -H "Content-Type: application/json" -d '{"id":4,"name":"Books"}' http://localhost:9000/categories

Cart:
1. Show all: curl http://localhost:9000/cart
2. Show by id: curl http://localhost:9000/cart/1  
3. Update: curl -X PUT -H "Content-Type: application/json" -d '{"productId":1,"quantity":5,"price":5.50}' http://localhost:9000/cart/1
4. Delete: curl -X DELETE http://localhost:9000/cart/1
5. Add: curl -X POST -H "Content-Type: application/json" -d '{"productId":1,"quantity":2,"price":5.50}' http://localhost:9000/cart