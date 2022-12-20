package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.commands.item

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.{FlagHandler, Handler}

class IdFlagHandler(val executor: Executor) extends FlagHandler {
  override def handle(flag: String): Option[Handler[String]] = {

    nextHandler
  }

  override def flagNames: Vector[String] = Vector("--id")
}
