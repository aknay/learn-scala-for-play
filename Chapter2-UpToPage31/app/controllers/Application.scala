package controllers

import play.api.mvc.{Action, Controller}
import play.api.mvc._

/**
  * Created by s43132 on 16/11/2016.
  */
class Application  extends Controller{

  def index = Action {
    //Ok("HelloWorld")
    //Ok(views.html.hello("Test"))
    Redirect(routes.Products.list())

  }

  def hello (name: String) = Action {
    Ok(views.html.hello(name))
  }



}
