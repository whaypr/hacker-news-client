package cz.cvut.fit.bioop.hackernewsclient

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class UtilsTest extends AnyFlatSpec with should.Matchers {

  "Utils.toInt" should "return some int if successfull and none if not" in {
    Utils.toInt("3") shouldEqual Some(3)
    Utils.toInt("3.3") shouldEqual None
    Utils.toInt("number") shouldEqual None
  }

  "Utils.getSlice" should "return only a potion of the passed sequence" in {
    App.page = 1
    App.pageSize = 10
    val data = 1 to 20
    val res = Utils.getSlice(data)
    res shouldEqual (1 to 10)
  }

  "Utils.bold" should "return bold text" in {
    val text = "bold me!"
    Utils.bold(text) shouldEqual s"\u001b[1m${text}\u001b[0m"
  }
}
