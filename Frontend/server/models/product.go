package models

import "gorm.io/gorm"

type Product struct {
	gorm.Model
	Name       string  `json:"name"`
	Price      float64 `json:"price"`
	CategoryId uint    `json:"category_id"`
}
