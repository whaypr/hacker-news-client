package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.flags.news

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.news.NewsShowsCommand
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.FlagHandler
import responsibilityChain.Handler

class ShowsFlagHandler(val argIterator: Iterator[String]) extends FlagHandler {
  override def handle(flag: String): Option[Handler[String]] = {
    super.handle(flag) match {
      case Some(v) => return Some(v)
      case _ =>
    }

    executor.get.addCommand(new NewsShowsCommand())
    None
  }

  override def flagNames: Vector[String] = Vector("--shows", "-o")
}
