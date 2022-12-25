package cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.user

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Command
import cz.cvut.fit.bioop.hackernewsclient.ui.pages.concrete.UserPage

/**
 * Class representing the user command
 */
class UserCommand(username: String, stories: Boolean) extends Command {
  override def execute: Unit = render(new UserPage(username, stories))
}
