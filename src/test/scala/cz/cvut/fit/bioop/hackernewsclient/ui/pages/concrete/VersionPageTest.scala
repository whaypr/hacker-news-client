package cz.cvut.fit.bioop.hackernewsclient.ui.pages.concrete

import org.scalatest.funsuite.AnyFunSuite

class VersionPageTest extends AnyFunSuite {

  test("Version page") {
    val page = new VersionPage()

    assert(page.render() == "1.0")
  }
}
