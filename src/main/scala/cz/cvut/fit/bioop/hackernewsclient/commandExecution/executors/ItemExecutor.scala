package cz.cvut.fit.bioop.hackernewsclient.commandExecution.executors

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.{Command, Executor}
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor.RetCode
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor.RetCode.RetCode
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.item.ItemCommand

/**
 * Class representing the item command executor
 */
class ItemExecutor extends Executor {
  var comments = false
  private var id: Option[Int] = None
  private var commands: Seq[Command] = Seq()

  def setId(id: Int) = this.id = Some(id)

  override def addCommand(command: Command): Unit = {
    commands = commands.appended(command)
  }

  override def execute: RetCode = {
    if (id.isEmpty)
      throw new Exception("Item id must be set!")

    addCommand(new ItemCommand(id.get, comments))

    commands.foreach(_.execute)
    RetCode.terminate
  }
}
