package cz.cvut.fit.bioop.hackernewsclient.ui.pages.concrete

import cz.cvut.fit.bioop.hackernewsclient.Utils
import org.scalatest.funsuite.AnyFunSuite

class ItemPageTest extends AnyFunSuite {

  test("Item page") {
    val page = new ItemPage(3, false)
    var res = "----------- STORY -----------\n"
    res += Utils.bold("Woz Interview: the early days of Apple") + " (http://www.foundersatwork.com/stevewozniak.html)\n"
    res += "  7 score by phyllis | 1 comments\n"


    val page2 = new ItemPage(15, false)
    val res2 = page2.render()

    assert(page.render() == res)
    assert(page2.render() == res2)
  }
}
