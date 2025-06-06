package main

import (
	"github.com/labstack/echo/v4"
	"github.com/labstack/echo/v4/middleware"

	"go-project/controllers"
	"go-project/database"
)

func main() {
	e := echo.New()
	database.Connect()

	e.Use(middleware.Logger())
	e.Use(middleware.Recover())

	e.GET("/products", controllers.GetProducts)
	e.GET("/products/:id", controllers.GetProduct)
	e.POST("/products", controllers.CreateProduct)
	e.PUT("/products/:id", controllers.UpdateProduct)
	e.DELETE("/products/:id", controllers.DeleteProduct)

	e.GET("/carts", controllers.GetCarts)
	e.GET("/carts/:id", controllers.GetCart)
	e.POST("/carts", controllers.CreateCart)

	e.GET("/categories", controllers.GetCategories)
	e.GET("/categories/:id", controllers.GetCategory)
	e.POST("/categories", controllers.CreateCategory)

	e.Logger.Fatal(e.Start(":8080"))
}
