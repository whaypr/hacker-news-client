package cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.item

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Command
import cz.cvut.fit.bioop.hackernewsclient.ui.pages.concrete.ItemPage

/**
 * Class representing the item command
 */
class ItemCommand(id: Int, comments: Boolean) extends Command {
  override def execute: Unit = render(new ItemPage(id, comments))
}
