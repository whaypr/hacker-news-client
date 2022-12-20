package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.commands.news

import cz.cvut.fit.bioop.hackernewsclient
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.NewsStoriesCommand
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.CommandLineParser
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.{FlagHandler, Handler}
import cz.cvut.fit.bioop.hackernewsclient.{App, Utils}

class StoriesFlagHandler(val executor: Executor, argIterator: Iterator[String]) extends FlagHandler {
  override def handle(flag: String): Option[Handler[String]] = {
    if (!flagNames.contains(flag))
      return nextHandler

    val arg = CommandLineParser.nextArg(argIterator).getOrElse({
      executor.addCommand(new NewsStoriesCommand("top")) // default behaviour
      return None
    })

    arg match {
      case "top" | "new" | "best" => executor.addCommand(new NewsStoriesCommand(arg))
      case _ => throw new Exception("Stories must have one of the following values: top | new | best")
    }

    None
  }

  override def flagNames: Vector[String] = Vector("--stories", "-s")
}
