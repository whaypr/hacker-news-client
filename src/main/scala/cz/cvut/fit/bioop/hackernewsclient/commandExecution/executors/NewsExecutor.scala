package cz.cvut.fit.bioop.hackernewsclient.commandExecution.executors

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.{Command, Executor}
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor.RetCode
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor.RetCode.RetCode
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.news.{NewsAsksCommand, NewsFirstCommand, NewsJobsCommand, NewsShowsCommand, NewsStoriesCommand}

class NewsExecutor extends Executor {
  private var endpointSet = false
  private var commands: Seq[Command] = Seq()

  override def addCommand(command: Command): Unit = {
    command match {
      case _:NewsFirstCommand | _:NewsStoriesCommand | _:NewsAsksCommand | _:NewsShowsCommand | _:NewsJobsCommand =>
        if (endpointSet)
          throw new Exception("Command option already set!")
        endpointSet = true
      //case x => throw new Exception(s"NewsExecutor cannot work with $x!")
      case _ =>
    }

    commands = commands.appended(command)
  }

  override def execute: RetCode = {
    commands.foreach(_.execute)
    RetCode.terminate
  }
}
