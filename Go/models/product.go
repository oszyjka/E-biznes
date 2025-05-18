package models

import "gorm.io/gorm"

type Product struct {
	gorm.Model
	Name       string  `json:"name"`
	Price      float64 `json:"price"`
	CategoryId uint    `json:"category_id"`
}

func MinPrice(min int) func(db *gorm.DB) *gorm.DB {
	return func(db *gorm.DB) *gorm.DB {
		return db.Where("price >= ?", min)
	}
}

func MaxPrice(max int) func(db *gorm.DB) *gorm.DB {
	return func(db *gorm.DB) *gorm.DB {
		return db.Where("price <= ?", max)
	}
}

func SortCategory(categoryId uint) func(db *gorm.DB) *gorm.DB {
	return func(db *gorm.DB) *gorm.DB {
		return db.Where("category_id = ?", categoryId)
	}
}

func SortByPriceAsc() func(db *gorm.DB) *gorm.DB {
	return func(db *gorm.DB) *gorm.DB {
		return db.Order("price ASC")
	}
}

func SortByPriceDesc() func(db *gorm.DB) *gorm.DB {
	return func(db *gorm.DB) *gorm.DB {
		return db.Order("price DESC")
	}
}
