package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.flags.news

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.news.NewsStoriesCommand
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.CommandLineParser
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.FlagHandler
import responsibilityChain.Handler

class StoriesFlagHandler(argIterator: Iterator[String]) extends FlagHandler {
  override def handle(flag: String): Option[Handler[String]] = {
    super.handle(flag) match {
      case Some(v) => return Some(v)
      case _ =>
    }

    val arg = CommandLineParser.nextArg(argIterator).getOrElse({
      executor.get.addCommand(new NewsStoriesCommand("top")) // default behaviour
      return None
    })

    arg match {
      case "top" | "new" | "best" => executor.get.addCommand(new NewsStoriesCommand(arg))
      case _ => throw new Exception("Stories must have one of the following values: top | new | best")
    }

    None
  }

  override def flagNames: Vector[String] = Vector("--stories", "-s")
}
