package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.commands.news

import cz.cvut.fit.bioop.hackernewsclient.App
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.{NewsAsksCommand, NewsStoriesCommand}
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.CommandLineParser
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.{FlagHandler, Handler}

class AsksFlagHandler(val executor: Executor, argIterator: Iterator[String]) extends FlagHandler {
  override def handle(flag: String): Option[Handler[String]] = {
    if (!flagNames.contains(flag))
      return nextHandler

    executor.addCommand(new NewsAsksCommand())
    None
  }

  override def flagNames: Vector[String] = Vector("--asks", "-a")
}
