package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.commands.app

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.AppHelpCommand
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.{FlagHandler, Handler}

class HelpFlagHandler(val executor: Executor) extends FlagHandler {
  override def handle(flag: String): Option[Handler[String]] = {
    if (!flagNames.contains(flag))
      return nextHandler

    executor.addCommand(new AppHelpCommand)
    None
  }

  override def flagNames: Vector[String] = Vector("--help", "-h")
}
