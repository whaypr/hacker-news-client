package cz.cvut.fit.bioop.hackernewsclient.commandExecution.executors

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.{Command, Executor}
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor.RetCode
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor.RetCode.RetCode
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.user.UserCommand

class UserExecutor extends Executor {
  var stories = false
  private var id: Option[String] = None
  private var commands: Seq[Command] = Seq()

  def setId(id: String) = this.id = Some(id)

  override def addCommand(command: Command): Unit = {
    commands = commands.appended(command)
  }

  override def execute: RetCode = {
    if (id.isEmpty)
      throw new Exception("User id must be set!")

    addCommand(new UserCommand(id.get, stories))

    commands.foreach(_.execute)
    RetCode.terminate
  }
}
