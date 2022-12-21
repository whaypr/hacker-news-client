package cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.news

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Command
import cz.cvut.fit.bioop.hackernewsclient.data.DataFetcher
import cz.cvut.fit.bioop.hackernewsclient.ui.pages.concrete.ItemPage

class NewsFirstCommand extends Command {
  override def execute: Unit = {
    val id = DataFetcher.fetchNews("maxitem")(0)
    render(new ItemPage(id, false))
  }
}
