package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.flags.item

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.executors.ItemExecutor
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.FlagHandler
import responsibilityChain.Handler

class CommentsFlagHandler extends FlagHandler {
  override def handle(flag: String): Option[Handler[String]] = {
    super.handle(flag) match {
      case Some(v) => return Some(v)
      case _ =>
    }

    executor.get match {
      case _: ItemExecutor => executor.get.asInstanceOf[ItemExecutor].comments = true
      case _ => throw new Exception("CommentsFlagHandler for item command MUST be used with Executor of type ItemExecutor!")
    }

    None
  }

  override def flagNames: Vector[String] = Vector("--comments", "-c")
}
