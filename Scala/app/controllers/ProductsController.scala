package controllers

import play.api.mvc._
import javax.inject._
import models.Product
import play.api.libs.json._

import scala.collection.mutable.ListBuffer

@Singleton
class ProductsController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  private val products = ListBuffer(
    Product(1, "Dress", "Clothes", 250.00),
    Product(2, "T-shirt", "Clothes", 100.00),
    Product(3, "Sofa", "Furniture", 3200.00)
  )

  def getAllProducts: Action[AnyContent] = Action {
    Ok(Json.toJson(products))
  }

  def getProductById(id: Long): Action[AnyContent] = Action {
    products.find(_.id == id) match {
      case Some(product) => Ok(Json.toJson(product))
      case None => NotFound(Json.obj("error" -> "Product doesn't exist."))
    }
  }

  def addProduct: Action[JsValue] = Action(parse.json) { request =>
    request.body.validate[Product].map { product =>
      products += product
      Created(Json.toJson(product))
    }.getOrElse {
      BadRequest(Json.obj("error" -> "Invalid format"))
    }
  }

  def updateProduct(id: Long): Action[JsValue] = Action(parse.json) { request =>
    request.body.validate[Product].map { updatedProduct =>
      products.indexWhere(_.id == id) match {
        case -1 => NotFound(Json.obj("error" -> "Product doesn't exist."))
        case index =>
          products.update(index, updatedProduct)
          Ok(Json.toJson(updatedProduct))
      }
    }.getOrElse {
        BadRequest(Json.obj("error" -> "Invalid format"))
    }
  }

  def deleteProduct(id: Long): Action[AnyContent] = Action {
    products.indexWhere(_.id == id) match {
      case -1 => NotFound(Json.obj("error" -> "Product doesn't exist."))
      case index =>
        products.remove(index)
        NoContent
    }
  }
}