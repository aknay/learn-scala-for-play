package controllers

import play.api.mvc._

/**
  * Created by aknay on 10/25/16.
  */
class Application extends Controller{

  def index = Action {
    Redirect(routes.Products.list())
  }
}
