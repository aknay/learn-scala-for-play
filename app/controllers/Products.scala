package controllers

import javax.inject.Inject
import play.api.mvc._
import play.api.mvc.Controller
import models.Product
import play.api.i18n.{I18nSupport, MessagesApi}

//Problem: could not find implicit value for parameter messages: play.api.i18n.Messages
//Solution: need to use 'with' keyword with I18nSupport; also need to use '@Inject' keyword

class Products @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {
    def list = Action { implicit request =>
        Ok(views.html.products.list(Product.findAll))
    }
}