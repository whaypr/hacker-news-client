package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.flags.app

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.AppPageSizeCommand
import cz.cvut.fit.bioop.hackernewsclient.Utils
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.CommandLineParser
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.FlagHandler
import responsibilityChain.Handler

class PageSizeFlagHandler(argIterator: Iterator[String]) extends FlagHandler {
  override def handle(flag: String): Option[Handler[String]] = {
    super.handle(flag) match {
      case Some(v) => return Some(v)
      case _ =>
    }

    val arg = CommandLineParser.nextArg(argIterator).getOrElse(
      throw new Exception("Page-size must have a number value!")
    )
    val num = Utils.toInt(arg).getOrElse(
      throw new Exception("Page-size must be a number!")
    )
    
    executor.get.addCommand(new AppPageSizeCommand(num))
    None
  }

  override def flagNames: Vector[String] = Vector("--page-size", "-ps")
}
