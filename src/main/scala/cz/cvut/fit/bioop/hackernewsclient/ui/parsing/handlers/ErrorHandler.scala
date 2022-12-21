package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor
import responsibilityChain.Handler

trait ErrorHandler extends Handler[String] {
  var executor: Option[Executor] = None
}
