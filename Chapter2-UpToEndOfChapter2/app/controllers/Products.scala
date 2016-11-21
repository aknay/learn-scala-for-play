package controllers

import javax.inject.Inject

import models.Product
import play.api.mvc.{Action, Controller, Flash}
import play.api.i18n.{I18nSupport, Messages, MessagesApi}
import play.api.data.Form
import play.api.data.Forms._

/**
  * Created by aknay on 16/11/2016.
  */
class Products @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {
  //read more about the form from: https://www.playframework.com/documentation/2.5.x/ScalaForms

  def hasValidLength(text: String): Boolean = text.length > 5 && text.length < 10

  //map values from form and add constraint to values of form
  private val productForm: Form[Product] = Form(
    mapping(
      "ean" -> longNumber.verifying("validation.ean.duplicate", Product.findByEan(_).isEmpty),
      "name" -> text.verifying("The length of Name must be between 5 and 10 ", hasValidLength _),
      "description" -> nonEmptyText
    )(Product.apply)(Product.unapply)
  )


  def list = Action { implicit request =>
    val products = Product.findAll
    Ok(views.html.Product.list(products))
  }

  def show(ean: Long) = Action { implicit request =>
    Product.findByEan(ean).map { product =>
      Ok(views.html.Product.detail(product))
    }.getOrElse(NotFound)
  }

  //Form will be passed to editProduct.scala.html
  def newProduct = Action { implicit request =>
    //if there is an error, flash error will be bind and will be sent together with the form
    val form = if (request.flash.get("error").isDefined) {
     val errorForm = productForm.bind(request.flash.data)
      errorForm
    }
    else {
      productForm
    }
    Ok(views.html.Product.editProduct(form))
  }

  //if the form has error and user tries to save, the page will be redirected to Products.newProduct
  //if the form is successfully saved, then the page will be redirected to Product.show
  def save = Action { implicit request =>
    val newProductForm = productForm.bindFromRequest()

    newProductForm.fold(
      hasErrors = { form =>
        Redirect(routes.Products.newProduct()).flashing(Flash(form.data) + ("error" -> Messages("validation.errors")))
      },
      success = {
        newProduct => Product.add(newProduct)
          val message = Messages("products.new.success", newProduct.name)
          Redirect(routes.Products.show(newProduct.ean)).flashing("success" -> message)
      })
  }

}
