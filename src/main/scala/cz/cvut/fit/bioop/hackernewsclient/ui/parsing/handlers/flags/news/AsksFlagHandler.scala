package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.flags.news

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.NewsAsksCommand
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.FlagHandler
import responsibilityChain.Handler

class AsksFlagHandler(argIterator: Iterator[String]) extends FlagHandler {
  override def handle(flag: String): Option[Handler[String]] = {
    super.handle(flag) match {
      case Some(v) => return Some(v)
      case _ =>
    }

    executor.get.addCommand(new NewsAsksCommand())
    None
  }

  override def flagNames: Vector[String] = Vector("--asks", "-a")
}
