package cz.cvut.fit.bioop.hackernewsclient.CommandExecution.Command

import cz.cvut.fit.bioop.hackernewsclient.App
import cz.cvut.fit.bioop.hackernewsclient.DataFetcher.DataFetcher
import cz.cvut.fit.bioop.hackernewsclient.UI.{Displayer, ItemDisplayer, NewsDisplayer, UserDisplayer}

abstract class Command {
  def execute: Unit
}


// APP -----------------------------------------------------
case class AppHelpCommand() extends Command {
  override def execute: Unit = {
    println(App.help)
  }
}

case class AppVersionCommand() extends Command {
  override def execute: Unit = {
    println(App.version)
  }
}

case class AppPageCommand(n: Int) extends Command {
  override def execute: Unit = App.page = n
}

case class AppPageSizeCommand(n: Int) extends Command {
  override def execute: Unit = App.pageSize = n
}

case class AppTimeToLiveCommand(n: Int) extends Command {
  override def execute: Unit = App.ttl = n
}

// NEWS -----------------------------------------------------
case class NewsFirstCommand(displayer: NewsDisplayer) extends Command {
  override def execute: Unit = displayer.ids = DataFetcher.fetchNews("maxitem")
}

case class NewsStoriesCommand(displayer: NewsDisplayer, value: String) extends Command {
  override def execute: Unit = {
    value match {
      case "top" => displayer.ids = DataFetcher.fetchNews("topstories")
      case "new" => displayer.ids = DataFetcher.fetchNews("newstories")
      case "best" => displayer.ids = DataFetcher.fetchNews("beststories")
    }
  }
}

case class NewsAsksCommand(displayer: NewsDisplayer) extends Command {
  override def execute: Unit = displayer.ids = DataFetcher.fetchNews("askstories")
}

case class NewsShowsCommand(displayer: NewsDisplayer) extends Command {
  override def execute: Unit = displayer.ids = DataFetcher.fetchNews("showstories")
}

case class NewsJobsCommand(displayer: NewsDisplayer) extends Command {
  override def execute: Unit = displayer.ids = DataFetcher.fetchNews("jobstories")
}


// ITEM -----------------------------------------------------D
case class ItemIdCommand(displayer: ItemDisplayer, id: Int) extends Command {
  override def execute: Unit = displayer.id = id
}

case class ItemCommentsCommand(displayer: ItemDisplayer) extends Command {
  override def execute: Unit = displayer.showComments = true
}


// USER -----------------------------------------------------
case class UserIdCommand(displayer: UserDisplayer, username: String) extends Command {
  override def execute: Unit = displayer.username = username
}

case class UserStoriesCommand(displayer: UserDisplayer) extends Command {
  override def execute: Unit = displayer.showStories = true
}
