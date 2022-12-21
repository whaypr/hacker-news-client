package cz.cvut.fit.bioop.hackernewsclient.ui.pages.concrete

import cz.cvut.fit.bioop.hackernewsclient.Utils
import org.scalatest.funsuite.AnyFunSuite

class UserPageTest extends AnyFunSuite {

  test("User page") {
    val page = new UserPage("pepe", false)

    var res = "----------- USER -----------\n"
    res += Utils.bold("pepe") + " (1)\n"
    res += "  created at 1176651111 | 1 posts\n"
    res+= "~NO DESCRIPTION~\n\n"

    assert(page.render() == res)
  }
}
