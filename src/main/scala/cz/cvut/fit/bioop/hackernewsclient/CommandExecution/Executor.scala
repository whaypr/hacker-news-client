package cz.cvut.fit.bioop.hackernewsclient.CommandExecution

import cz.cvut.fit.bioop.hackernewsclient.CommandExecution.Command.{AppHelpCommand, AppVersionCommand, Command, ItemIdCommand, NewsAsksCommand, NewsFirstCommand, NewsJobsCommand, NewsShowsCommand, NewsStoriesCommand, UserIdCommand}
import cz.cvut.fit.bioop.hackernewsclient.CommandExecution.Executor.RetCode
import cz.cvut.fit.bioop.hackernewsclient.CommandExecution.Executor.RetCode.RetCode

trait Executor {
  def addCommand(command: Command): Unit
  def execute: RetCode
}


object Executor {
  object RetCode extends Enumeration {
    type RetCode = Value

    val terminate = Value("Terminate") // ID = 0
    val continue = Value("Continue") // ID = 1
  }
}


// APP
class AppExecutor extends Executor {
  private var _invalidOption = false

  private var helpCommand: Option[AppHelpCommand] = None
  private var versionCommand: Option[AppVersionCommand] = None
  private var otherCommands: Seq[Command] = Seq()


  override def addCommand(command: Command): Unit = {
    command match {
      case AppHelpCommand() => helpCommand = Some(new AppHelpCommand)
      case AppVersionCommand()  => versionCommand = Some(new AppVersionCommand)
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


// NEWS
class NewsExecutor extends Executor {
  private var endpointSet = false
  private var commands: Seq[Command] = Seq()

  override def addCommand(command: Command): Unit = {
    command match {
      case NewsFirstCommand(_) | NewsStoriesCommand(_,_) | NewsAsksCommand(_) | NewsShowsCommand(_) | NewsJobsCommand(_) =>
        if (endpointSet)
          throw new Exception("Command option already set!")
        endpointSet = true
      case _ =>
    }

    commands = commands.appended(command)
  }

  override def execute: RetCode = {
    commands.foreach(_.execute)
    RetCode.continue
  }
}


// ITEM
class ItemExecutor extends Executor {
  var idSet = false
  private var commands: Seq[Command] = Seq()

  override def addCommand(command: Command): Unit = {
    command match {
      case ItemIdCommand(_,_) => idSet = true
      case _ =>
    }

    commands = commands.appended(command)
  }

  override def execute: RetCode = {
    if (!idSet)
      throw new Exception("Item id must be set!")

    commands.foreach(_.execute)
    RetCode.terminate
  }
}


// USER
class UserExecutor extends Executor {
  var idSet = false
  private var commands: Seq[Command] = Seq()

  override def addCommand(command: Command): Unit = {
    command match {
      case UserIdCommand(_,_) => idSet = true
      case _ =>
    }

    commands = commands.appended(command)
  }

  override def execute: RetCode = {
    if (!idSet)
      throw new Exception("User id must be set!")

    commands.foreach(_.execute)
    RetCode.terminate
  }
}
