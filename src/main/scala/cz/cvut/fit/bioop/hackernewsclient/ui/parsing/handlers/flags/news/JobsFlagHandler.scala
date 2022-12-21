package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.flags.news

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.NewsJobsCommand
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.FlagHandler
import responsibilityChain.Handler

class JobsFlagHandler(argIterator: Iterator[String]) extends FlagHandler {
  override def handle(flag: String): Option[Handler[String]] = {
    super.handle(flag) match {
      case Some(v) => return Some(v)
      case _ =>
    }

    executor.get.addCommand(new NewsJobsCommand())
    None
  }

  override def flagNames: Vector[String] = Vector("--jobs", "-j")
}
