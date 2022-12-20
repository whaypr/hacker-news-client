package cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands

import cz.cvut.fit.bioop.hackernewsclient.data.DataFetcher
import cz.cvut.fit.bioop.hackernewsclient.ui.pages.TextPage
import cz.cvut.fit.bioop.hackernewsclient.ui.pages.concrete.{HelpPage, ItemPage, UserPage, VersionPage}
import cz.cvut.fit.bioop.hackernewsclient.{App, Utils}

trait Command {
  def execute: Unit

  def render(renderer: TextPage): Unit = {
    val output = renderer.render()
    println(output)
  }
}


// APP -----------------------------------------------------
class AppHelpCommand() extends Command {
  override def execute: Unit = render(new HelpPage)
}

class AppVersionCommand() extends Command {
  override def execute: Unit = render(new VersionPage)
}

class AppPageCommand(n: Int) extends Command {
  override def execute: Unit = App.page = n
}

class AppPageSizeCommand(n: Int) extends Command {
  override def execute: Unit = App.pageSize = n
}

class AppTimeToLiveCommand(n: Int) extends Command {
  override def execute: Unit = App.ttl = n
}


// NEWS -----------------------------------------------------
class NewsFirstCommand extends Command {
  override def execute: Unit = {
    val id = DataFetcher.fetchNews("maxitem")(0)
    render(new ItemPage(id, false))
  }
}

class NewsStoriesCommand(newsType: String) extends Command {
  override def execute: Unit = {
    newsType match {
      case "top" =>
        val ids = DataFetcher.fetchNews("topstories")
        Utils.getSlice(ids).foreach{id => render(new ItemPage(id, false))}
      case "new" =>
        val ids = DataFetcher.fetchNews("newstories")
        Utils.getSlice(ids).foreach { id => render(new ItemPage(id, false)) }
      case "best" =>
        val ids = DataFetcher.fetchNews("beststories")
        Utils.getSlice(ids).foreach { id => render(new ItemPage(id, false)) }
    }
  }
}

class NewsAsksCommand extends Command {
  override def execute: Unit = {
    val ids = DataFetcher.fetchNews("askstories")
    Utils.getSlice(ids).foreach{id => render(new ItemPage(id, false))}
  }
}

class NewsShowsCommand extends Command {
  override def execute: Unit = {
    val ids = DataFetcher.fetchNews("showstories")
    Utils.getSlice(ids).foreach { id => render(new ItemPage(id, false)) }
  }
}

class NewsJobsCommand extends Command {
  override def execute: Unit = {
    val ids = DataFetcher.fetchNews("jobstories")
    Utils.getSlice(ids).foreach { id => render(new ItemPage(id, false)) }
  }
}


// ITEM -----------------------------------------------------
class ItemCommand(id: Int, comments: Boolean) extends Command {
  override def execute: Unit = render(new ItemPage(id, comments))
}


// USER -----------------------------------------------------
class UserCommand(username: String, stories: Boolean) extends Command {
  override def execute: Unit = render(new UserPage(username, stories))
}

