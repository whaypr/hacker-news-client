package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.flags.item

import cz.cvut.fit.bioop.hackernewsclient.Utils
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.executors.ItemExecutor
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.CommandLineParser
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.FlagHandler
import responsibilityChain.Handler

class IdFlagHandler(argIterator: Iterator[String]) extends FlagHandler {
  override def handle(flag: String): Option[Handler[String]] = {
    super.handle(flag) match {
      case Some(v) => return Some(v)
      case _ =>
    }

    val arg = CommandLineParser.nextArg(argIterator).getOrElse(
      throw new Exception("Id must have a number value!")
    )
    val num = Utils.toInt(arg).getOrElse(
      throw new Exception("Id must be a number!")
    )

    executor.get match {
      case _: ItemExecutor => executor.get.asInstanceOf[ItemExecutor].setId(num)
      case _ => throw new Exception("IdFlagHandler for item command MUST be used with Executor of type ItemExecutor!")
    }

    None
  }

  override def flagNames: Vector[String] = Vector("--id")
}
