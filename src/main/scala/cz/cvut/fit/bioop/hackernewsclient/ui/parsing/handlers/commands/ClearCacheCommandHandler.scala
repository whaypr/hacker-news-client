package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.commands

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.clearCache.ClearCacheCommand
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.CommandHandler
import responsibilityChain.Handler

/**
 * Handler for the clear cache command
 * @param executor
 */
class ClearCacheCommandHandler(val executor: Executor) extends CommandHandler {
  override def handle(item: String): Option[Handler[String]] = {
    executor.addCommand(new ClearCacheCommand())
    nextHandler
  }
}
