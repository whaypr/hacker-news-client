package cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.news

import cz.cvut.fit.bioop.hackernewsclient.Utils
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Command
import cz.cvut.fit.bioop.hackernewsclient.data.DataFetcher
import cz.cvut.fit.bioop.hackernewsclient.ui.pages.concrete.ItemPage

/**
 * Class representing the news's command stories flag
 */
class NewsStoriesCommand(newsType: String) extends Command {
  override def execute: Unit =
    newsType match {
      case "top" =>
        val ids = DataFetcher.get.fetchNews("topstories")
        Utils.getSlice(ids).foreach { id =>
          render(new ItemPage(id, false))
        }
      case "new" =>
        val ids = DataFetcher.get.fetchNews("newstories")
        Utils.getSlice(ids).foreach { id =>
          render(new ItemPage(id, false))
        }
      case "best" =>
        val ids = DataFetcher.get.fetchNews("beststories")
        Utils.getSlice(ids).foreach { id =>
          render(new ItemPage(id, false))
        }
    }
}
