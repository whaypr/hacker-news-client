package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.errors

import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.ErrorHandler
import responsibilityChain.Handler

/**
 * Error handler for the news command
 * @param executor
 */
class NewsErrorHandler extends ErrorHandler {
  override def handle(item: String): Option[Handler[String]] = {
    throw new Exception("Invalid news option!")
  }
}
