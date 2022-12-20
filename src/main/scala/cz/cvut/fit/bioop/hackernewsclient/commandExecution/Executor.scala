package cz.cvut.fit.bioop.hackernewsclient.commandExecution

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.{AppHelpCommand, AppVersionCommand, Command, ItemCommand, NewsAsksCommand, NewsFirstCommand, NewsJobsCommand, NewsShowsCommand, NewsStoriesCommand, UserCommand}
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor.RetCode
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor.RetCode.RetCode

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


// NEWS
class NewsExecutor extends Executor {
  private var endpointSet = false
  private var commands: Seq[Command] = Seq()

  override def addCommand(command: Command): Unit = {
    command match {
      case _:NewsFirstCommand | _:NewsStoriesCommand | _:NewsAsksCommand | _:NewsShowsCommand | _:NewsJobsCommand =>
        if (endpointSet)
          throw new Exception("Command option already set!")
        endpointSet = true
      case x => throw new Exception(s"NewsExecutor cannot work with $x!")
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


// USER
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
