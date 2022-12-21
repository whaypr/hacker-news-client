package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.errors

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.AppExecutor
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.ErrorHandler
import responsibilityChain.Handler

class AppErrorHandler extends ErrorHandler {
  override def handle(item: String): Option[Handler[String]] = {
    executor.get match {
      case _: AppExecutor => executor.get.asInstanceOf[AppExecutor].invalidOption
      case _ => throw new Exception("AppErrorHandler MUST be used with Executor of type AppExecutor!")
    }

    None
  }
}
