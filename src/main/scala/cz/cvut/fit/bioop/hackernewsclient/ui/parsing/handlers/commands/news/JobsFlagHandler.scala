package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.commands.news

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.{NewsJobsCommand, NewsShowsCommand}
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.{FlagHandler, Handler}

class JobsFlagHandler(val executor: Executor, argIterator: Iterator[String]) extends FlagHandler {
  override def handle(flag: String): Option[Handler[String]] = {
    if (!flagNames.contains(flag))
      return nextHandler

    executor.addCommand(new NewsJobsCommand())
    None
  }

  override def flagNames: Vector[String] = Vector("--jobs", "-j")
}
