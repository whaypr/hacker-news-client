package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.commands.clear_cache

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.{CommandHandler, Handler}

class ClearCacheCommandHandler(val executor: Executor) extends CommandHandler {
  override def commandName: String = "news"

  override def handle(item: String): Option[Handler[String]] = {

    nextHandler
  }
}
