package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.flags.app

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.app.AppHelpCommand
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.FlagHandler
import responsibilityChain.Handler

/**
 * Handler for the app's help flag
 * @param executor
 */
class HelpFlagHandler extends FlagHandler {
  override def handle(flag: String): Option[Handler[String]] = {
    super.handle(flag) match {
      case Some(v) => return Some(v)
      case _ =>
    }

    executor.get.addCommand(new AppHelpCommand)
    None
  }

  override def flagNames: Vector[String] = Vector("--help", "-h")
}
