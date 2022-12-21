package cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.app

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Command
import cz.cvut.fit.bioop.hackernewsclient.ui.pages.concrete.VersionPage

class AppVersionCommand() extends Command {
  override def execute: Unit = render(new VersionPage)
}
