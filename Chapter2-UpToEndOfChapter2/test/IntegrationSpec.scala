import org.scalatestplus.play._

/**
  * Created by aknay on 18/11/2016.
  */

class IntegrationSpec extends PlaySpec with OneServerPerSuite with OneBrowserPerSuite with HtmlUnitFactory{
  "The OneBrowserPerTest trait" must {
    "provide a web driver" in {
      go to s"http://localhost:9000/products"
      pageTitle mustBe "products.list"
      click on find(linkText("New")).value
      eventually {pageTitle mustBe "Product details"}
    }
  }
}
