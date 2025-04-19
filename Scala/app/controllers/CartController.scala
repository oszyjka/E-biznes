package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.CartItem
import scala.collection.mutable.ListBuffer

@Singleton
class CartController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  private val cart = ListBuffer[CartItem]()

  def getAllItems: Action[AnyContent] = Action {
    val total = cart.map(item => item.quantity * item.price).sum
    Ok(Json.obj(
      "items" -> cart,
      "totalPrice" -> total
    ))
  }

  def getItemById(productId: Int): Action[AnyContent] = Action {
    cart.find(_.productId == productId) match {
      case Some(item) =>
       val totalPrice = item.quantity * item.price
       Ok(Json.obj(
        "item" -> Json.toJson(item),
        "totalPrice" -> totalPrice
      ))
      case None => NotFound(Json.obj("error" -> "Item doesn't exists. in cart"))
    }
  }

  def addItem: Action[JsValue] = Action(parse.json) { request =>
    request.body.validate[CartItem].map { item => 
        cart += item
        Created(Json.toJson(item))
      }.getOrElse {
        BadRequest(Json.obj("error" -> "Invalid format"))
      }
  }

  def updateItem(productId: Int): Action[JsValue] = Action(parse.json) { request =>
    request.body.validate[CartItem].map { updatedItem =>
        cart.indexWhere(_.productId == productId) match {
          case -1 => NotFound(Json.obj("error" -> "Item doesn't exist."))
          case index =>
            cart.update(index, updatedItem)
            Ok(Json.toJson(updatedItem))
        }
      }.getOrElse {
        BadRequest(Json.obj("error" -> "Invalid JSON"))
      }
  }

  def deleteItem(productId: Int): Action[AnyContent] = Action {
    cart.indexWhere(_.productId == productId) match {
      case -1 => NotFound(Json.obj("error" -> "Item doesn't exists."))
      case index =>
        cart.remove(index)
        NoContent
    }
  }
}