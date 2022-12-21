package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.flags.user

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.executors.UserExecutor
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
      throw new Exception("Page must have a number value!")
    )

    executor.get match {
      case _: UserExecutor => executor.get.asInstanceOf[UserExecutor].setId(arg)
      case _ => throw new Exception("IdFlagHandler for user command MUST be used with Executor of type UserExecutor!")
    }

    None
  }

  override def flagNames: Vector[String] = Vector("--id")
}
