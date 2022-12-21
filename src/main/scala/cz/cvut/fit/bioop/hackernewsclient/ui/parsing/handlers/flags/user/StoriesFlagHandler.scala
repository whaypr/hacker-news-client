package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.flags.user

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.UserExecutor
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.FlagHandler
import responsibilityChain.Handler

class StoriesFlagHandler extends FlagHandler {
  override def handle(flag: String): Option[Handler[String]] = {
    super.handle(flag) match {
      case Some(v) => return Some(v)
      case _ =>
    }

    executor.get match {
      case _: UserExecutor => executor.get.asInstanceOf[UserExecutor].stories = true
      case _ => throw new Exception("StoriesFlagHandler for user command MUST be used with Executor of type UserExecutor!")
    }

    None
  }

  override def flagNames: Vector[String] = Vector("--submit")
}
