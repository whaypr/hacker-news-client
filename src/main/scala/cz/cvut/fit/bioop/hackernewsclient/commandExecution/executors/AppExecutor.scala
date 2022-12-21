package cz.cvut.fit.bioop.hackernewsclient.commandExecution.executors

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.{Command, Executor}
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor.RetCode
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.app.{AppHelpCommand, AppVersionCommand}

class AppExecutor extends Executor {
  private var _invalidOption = false

  private var helpCommand: Option[AppHelpCommand] = None
  private var versionCommand: Option[AppVersionCommand] = None
  private var otherCommands: Seq[Command] = Seq()


  override def addCommand(command: Command): Unit = {
    command match {
      case _: AppHelpCommand => helpCommand = Some(new AppHelpCommand)
      case _: AppVersionCommand  => versionCommand = Some(new AppVersionCommand)
      case _  => otherCommands = otherCommands.appended(command)
    }
  }


  override def execute(): RetCode.RetCode = {
    if (helpCommand.isDefined) {
      helpCommand.get.execute
      return RetCode.terminate
    }

    if (versionCommand.isDefined) {
      versionCommand.get.execute
      return RetCode.terminate
    }

    if (_invalidOption)
      throw new Exception("Invalid app option!")

    otherCommands.foreach(_.execute)

    RetCode.continue
  }


  def invalidOption =
    _invalidOption = true
}
