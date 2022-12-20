package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.commands.app

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.AppVersionCommand
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.{FlagHandler, Handler}

class VersionFlagHandler(val executor: Executor) extends FlagHandler {
  override def handle(flag: String): Option[Handler[String]] = {
    if (!flagNames.contains(flag))
      return nextHandler

    executor.addCommand(new AppVersionCommand)
    None
  }

  override def flagNames: Vector[String] = Vector("--version", "-v")
}
