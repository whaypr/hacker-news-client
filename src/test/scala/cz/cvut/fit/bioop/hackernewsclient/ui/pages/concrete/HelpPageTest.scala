package cz.cvut.fit.bioop.hackernewsclient.ui.pages.concrete

import org.scalatest.funsuite.AnyFunSuite

class HelpPageTest extends AnyFunSuite {

  test("Help page") {
    val page = new HelpPage()

    assert(page.render() ==
      """
        |hnc [app-options] [command command-options]
        |
        |app-options:
        |    [--help] [--version] [--page=] [--page-size=] [--time-to-live=]
        |        --help            show this help
        |        --version         show ap version
        |        --page            set number of displayed page
        |        --page-size       set size of displayed page
        |        --time-to-live    set ttl of cached items
        |
        |command and their command-options:
        |    news [--first | --stories=(top|new|best) | --asks | --shows | --jobs]
        |        --first           get the newest item
        |        --stories         get new stories
        |        --asks            get new asks
        |        --shows           get new shows
        |        --jobs            get new jobs
        |    item --id= [--comments]
        |        --id              specify item id
        |        --comments        show item comments
        |    user --id= [--stories]
        |        --id               specify user id (username)
        |        --submit          show user submitted items
        |    clear-cache           clear cache with fetched items
        |""".stripMargin
    )
  }
}
