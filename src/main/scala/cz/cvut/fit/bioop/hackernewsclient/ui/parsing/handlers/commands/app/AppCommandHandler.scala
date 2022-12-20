package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.commands.app

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.{CommandHandler, Handler}

class AppCommandHandler(val executor: Executor) extends CommandHandler {
  override def commandName: String = ""

  override def handle(item: String): Option[Handler[String]] = {

    nextHandler
  }
}
