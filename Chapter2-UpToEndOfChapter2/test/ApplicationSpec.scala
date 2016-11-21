import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._

/**
  * Add your spec here.
  * You can mock out a whole application including requests, plugins etc.
  * For more information, consult the wiki.
  */
class ApplicationSpec extends PlaySpec with OneAppPerTest {

  "Routes" should {

    "send 404 on a bad request" in  {
      route(app, FakeRequest(GET, "/boum")).map(status) mustBe Some(NOT_FOUND)
    }

  }

  "HomeController" should {
    "render the index page" in {
      val home = route(app, FakeRequest(GET, "/")).get
      status(home) mustBe SEE_OTHER
    }
  }

  "ProductsController" should {
    "render page products" in {
      val productsRoute = route(app, FakeRequest(GET, "/products")).get
      status(productsRoute) mustBe OK
    }

    "render page detail products" in {
      val productsRoute = route(app, FakeRequest(GET, "/product/5010255079763")).get
      status(productsRoute) mustBe OK
    }

    "render page new product" in {
      val productsRoute = route(app, FakeRequest(GET, "/products/new")).get
      status(productsRoute) mustBe OK
    }

  }

}