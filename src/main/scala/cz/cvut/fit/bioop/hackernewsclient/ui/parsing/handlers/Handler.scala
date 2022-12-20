package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers

import scala.annotation.tailrec

trait Handler[T] {
  /**
   * Handles an item and may return another handler
   * @param item An item to process
   * @return Optionally the next handler
   */
  def handle(item: T): Option[Handler[T]]

  /**
   * Sets a next handler
   * @param nextHandler The next handler
   * @return The next handler
   */
  def setNext(nextHandler: Handler[T]): Handler[T] = {
    this.nextHandler = Some(nextHandler)
    nextHandler
  }

  /**
   * Internally saved next handler
   */
  var nextHandler: Option[Handler[T]] = None
}


object Handler {

  /**
   * Resolves all handlers in the chain
   * @param initialHandler The initial handler
   * @tparam T Type of the handler chain
   */
  def resolveAll[T](initialHandler: Handler[T], value: T): Unit = {

    //Loop in handlers
    @tailrec
    def handlerLoop(currentHandler: Handler[T]): Unit = {
      currentHandler.handle(value) match {
        case Some(nextHandler) => handlerLoop(nextHandler)
        case None =>
      }
    }

    handlerLoop(initialHandler)
  }
}
