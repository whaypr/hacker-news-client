package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.commands.news

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.{AppHelpCommand, NewsFirstCommand}
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.{FlagHandler, Handler}

class FirstFlagHandler(val executor: Executor) extends FlagHandler {
  override def handle(flag: String): Option[Handler[String]] = {
    if (!flagNames.contains(flag))
      return nextHandler

    executor.addCommand(new NewsFirstCommand())
    None
  }

  override def flagNames: Vector[String] = Vector("--first", "-f")
}
