import controllers.routes
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._

/**
  * Add your spec here.
  * You can mock out a whole application including requests, plugins etc.
  * For more information, consult the wiki.
  */
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication {
      route(app, FakeRequest(GET, "/boum")) must beSome.which(status(_) == NOT_FOUND)
    }

    "render the index page" in new WithApplication {
      val home = route(app, FakeRequest(GET, "/")).get
      status(home) must equalTo(SEE_OTHER) //SEE_OTHER is Http Redirect 303 code
    }

    "render the hello page" in new WithApplication {
      val hello = route(app, FakeRequest(GET, "/hello?n=Play!")).get
      status(hello) must equalTo(OK)
    }

    "render product list page" in new WithApplication {
      var productList = route(app, FakeRequest(GET, "/products")).get
      status(productList) must equalTo(OK)
    }

    "render product detail page" in new WithApplication {
      var productDetail = route(app, FakeRequest(GET, "/product/5010255079763")).get
      status(productDetail) must equalTo(OK)
    }
  }

}
