package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.commands.app

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.AppPageCommand
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor
import cz.cvut.fit.bioop.hackernewsclient.Utils
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.CommandLineParser
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.{FlagHandler, Handler}

class PageFlagHandler(val executor: Executor, argIterator: Iterator[String]) extends FlagHandler {
  override def handle(flag: String): Option[Handler[String]] = {
    if (!flagNames.contains(flag))
      return nextHandler

    val arg = CommandLineParser.nextArg(argIterator).getOrElse(
      throw new Exception("Page must have a number value!")
    )
    val num = Utils.toInt(arg).getOrElse(
      throw new Exception("Page must be a number!")
    )

    executor.addCommand(new AppPageCommand(num))
    None
  }

  override def flagNames: Vector[String] = Vector("--page", "-pg")
}
