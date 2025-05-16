package controllers

import (
	"net/http"
	"strconv"

	"github.com/labstack/echo/v4"
)

type Product struct {
	id       int    `json:"id"`
	Name     string `json:"name"`
	Price    int    `json:"price"`
	Category string `json:"category"`
}

var products []Product
var idx = 1

func GetProducts(c echo.Context) error {
	return c.JSON(http.StatusOK, products)
}

func GetProduct(c echo.Context) error {
	idCheck, err := strconv.Atoi(c.Param("id"))
	if err != nil {
		return c.JSON(http.StatusBadRequest, map[string]string{"error": "Invalid id"})
	}

	for _, product := range products {
		if product.id == idCheck {
			return c.JSON(http.StatusOK, product)
		}
	}
	return c.JSON(http.StatusNotFound, "Product not found")
}

func CreateProduct(c echo.Context) error {
	product := new(Product)
	if err := c.Bind(&product); err != nil {
		return c.JSON(http.StatusBadRequest, map[string]string{"error": "Invalid input"})
	}
	product.id = idx
	idx++
	products = append(products, *product)
	return c.JSON(http.StatusCreated, product)
}

func UpdateProduct(c echo.Context) error {
	idCheck, err := strconv.Atoi(c.Param("id"))
	if err != nil {
		return c.JSON(http.StatusBadRequest, map[string]string{"error": "Invalid id"})
	}
	updated := new(Product)
	if err := c.Bind(updated); err != nil {
		return err
	}

	for i, product := range products {
		if product.id == idCheck {
			products[i].Name = updated.Name
			products[i].Price = updated.Price
			products[i].Category = updated.Category
			return c.JSON(http.StatusOK, products[i])
		}
	}
	return c.JSON(http.StatusNotFound, "Product not found")
}

func DeleteProduct(c echo.Context) error {
	idCheck, err := strconv.Atoi(c.Param("id"))
	if err != nil {
		return c.JSON(http.StatusBadRequest, map[string]string{"error": "Invalid id"})
	}

	for i, product := range products {
		if product.id == idCheck {
			products = append(products[:i], products[i+1:]...)
			return c.NoContent(http.StatusNoContent)
		}
	}
	return c.JSON(http.StatusNotFound, "Product not found")
}
