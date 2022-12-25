package cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.app

import cz.cvut.fit.bioop.hackernewsclient.App
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Command

/**
 * Class representing the app's page flag
 */
class AppPageCommand(n: Int) extends Command {
  override def execute: Unit = App.page = n
}
