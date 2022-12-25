package cz.cvut.fit.bioop.hackernewsclient.commandExecution.executors

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor.RetCode
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor.RetCode.RetCode
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.user.UserCommand
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.{Command, Executor}

/**
 * Class representing the clear cache command executor
 */
class ClearCacheExecutor extends Executor {
  private var command: Option[Command] = None

  override def addCommand(command: Command): Unit = {
    this.command = Some(command)
  }

  override def execute: RetCode = {
    command match {
      case Some(c) => c.execute
      case None => throw new Exception("No command supplied to ClearCacheExecutor!")
    }

    RetCode.terminate
  }
}
