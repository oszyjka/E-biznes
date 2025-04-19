package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.Category
import scala.collection.mutable.ListBuffer

@Singleton
class CategoryController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  private val categories = ListBuffer(
    Category(1, "Clothes"),
    Category(2, "Furniture"),
    Category(3, "Electronics")
  )

  def getAllCategories: Action[AnyContent] = Action {
    Ok(Json.toJson(categories))
  }

  def getCategoryById(id: Int): Action[AnyContent] = Action {
    categories.find(_.id == id) match {
      case Some(category) => Ok(Json.toJson(category))
      case None => NotFound(Json.obj("error" -> "Category doesn't exists."))
    }
  }

  def createCategory: Action[JsValue] = Action(parse.json) { request =>
    request.body.validate[Category].map { category => 
        categories += category
        Created(Json.toJson(category))
      }.getOrElse {
        BadRequest(Json.obj("error" -> "Invalid format"))
      }
  }

  def updateCategory(id: Int): Action[JsValue] = Action(parse.json) { request =>
    request.body.validate[Category].map { updatedCategory => 
        categories.indexWhere(_.id == id) match {
          case -1 => NotFound(Json.obj("error" -> "Category doesn't exists."))
          case index =>
            categories.update(index, updatedCategory)
            Ok(Json.toJson(updatedCategory))
        }
      }.getOrElse {
          BadRequest(Json.obj("error" -> "Invalid JSON"))
      }
    
  }

  def deleteCategory(id: Int): Action[AnyContent] = Action {
    categories.indexWhere(_.id == id) match {
      case -1 => NotFound(Json.obj("error" -> "Category doesn't exists."))
      case index =>
        categories.remove(index)
        NoContent
    }
  }
}