package models

import play.api.libs.json.{Json, OFormat}

case class CartItem(productId: Int, quantity: Int, price: Double)

object CartItem {
  implicit val format: OFormat[CartItem] = Json.format[CartItem]
}