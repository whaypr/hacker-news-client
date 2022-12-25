package cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.app

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Command
import cz.cvut.fit.bioop.hackernewsclient.ui.pages.concrete.HelpPage

/**
 * Class representing the app's help flag
 */
class AppHelpCommand() extends Command {
  override def execute: Unit = render(new HelpPage)
}
