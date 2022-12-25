package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor
import responsibilityChain.Handler

/**
 * Handler for errors
 */
trait ErrorHandler extends Handler[String] {
  var executor: Option[Executor] = None
}
