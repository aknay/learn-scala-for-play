package controllers

import javax.inject.Inject

import models.Product
import play.api.mvc.{Action, Controller}
import play.api.i18n.{I18nSupport, MessagesApi}

/**
  * Created by s43132 on 16/11/2016.
  */
class Products @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def list = Action { implicit request =>
    val products = Product.findAll
    Ok(views.html.Product.list(products))
  }

  def show(ean: Long) = Action { implicit request =>
    Product.findByEan(ean).map{ product =>
      Ok(views.html.Product.detail(product))
    }.getOrElse(NotFound)
    }

}
