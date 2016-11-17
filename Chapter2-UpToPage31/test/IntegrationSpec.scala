import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import org.openqa.selenium.htmlunit.HtmlUnitDriver

import play.api.test._
import play.api.test.Helpers._

/**
 * add your integration spec here.
 * An integration test will fire up a whole play application in a real (or headless) browser
 */
@RunWith(classOf[JUnitRunner])
class IntegrationSpec extends Specification {

  "Application" should {

    "work from within a browser" in new WithBrowser {

      browser.goTo("http://localhost:" + port)
      browser.pageSource must contain("Giant Paperclips")

      browser.goTo("http://localhost:9000/hello?n=Play!")
      browser.pageSource must contain("Play!")

      browser.goTo("http://localhost:9000/")
      browser.pageSource must contain("Giant Paperclips")

      browser.goTo("http://localhost:9000/product/5010255079763")
      browser.pageSource must contain("5010255079763")

    }
  }
}
