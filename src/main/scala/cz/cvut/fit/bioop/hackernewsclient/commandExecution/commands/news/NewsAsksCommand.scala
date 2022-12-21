package cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.news

import cz.cvut.fit.bioop.hackernewsclient.Utils
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Command
import cz.cvut.fit.bioop.hackernewsclient.data.DataFetcher
import cz.cvut.fit.bioop.hackernewsclient.ui.pages.concrete.ItemPage

class NewsAsksCommand extends Command {
  override def execute: Unit = {
    val ids = DataFetcher.fetchNews("askstories")
    Utils.getSlice(ids).foreach { id =>
      render(new ItemPage(id, false))
    }
  }
}
