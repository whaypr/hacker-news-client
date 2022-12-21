package cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.app

import cz.cvut.fit.bioop.hackernewsclient.App
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Command

class AppTimeToLiveCommand(n: Int) extends Command {
  override def execute: Unit = App.ttl = n
}
