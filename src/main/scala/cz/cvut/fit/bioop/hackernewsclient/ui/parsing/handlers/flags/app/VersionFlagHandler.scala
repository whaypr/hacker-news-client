package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.flags.app

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.app.AppVersionCommand
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.FlagHandler
import responsibilityChain.Handler

/**
 * Handler for the app's version flag
 * @param executor
 */
class VersionFlagHandler extends FlagHandler {
  override def handle(flag: String): Option[Handler[String]] = {
    super.handle(flag) match {
      case Some(v) => return Some(v)
      case _ =>
    }

    executor.get.addCommand(new AppVersionCommand)
    None
  }

  override def flagNames: Vector[String] = Vector("--version", "-v")
}
